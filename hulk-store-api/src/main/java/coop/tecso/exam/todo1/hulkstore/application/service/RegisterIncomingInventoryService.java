package coop.tecso.exam.todo1.hulkstore.application.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import coop.tecso.exam.todo1.hulkstore.application.request.RegisterIncomingInventoryRequest;
import coop.tecso.exam.todo1.hulkstore.domain.model.Movement;
import coop.tecso.exam.todo1.hulkstore.domain.model.MovementType;
import coop.tecso.exam.todo1.hulkstore.domain.service.MovementService;
import coop.tecso.exam.todo1.hulkstore.domain.service.ProductService;
import coop.tecso.exam.todo1.hulkstore.domain.validator.FieldValidator;

@Service

public class RegisterIncomingInventoryService {

	private MovementService movementService;
	private ProductService productService;
	
	public RegisterIncomingInventoryService(MovementService movementService, ProductService productService) {
		this.movementService = movementService;
		this.productService = productService;
	}

	public void execute(RegisterIncomingInventoryRequest request) {
		
		checkNotNull(request);
		
		checkIfQuantityIsGreaterThanZero( request.getQuantity() );

		checkIfProductExists( request.getProductId() );

		Movement movement = convertToMovement(request);
		
		movementService.saveMovement(movement);
		
	}

	private void checkIfProductExists(String productId) {
		productService.checkIfProductExists(productId);
	}
	
	private void checkNotNull(RegisterIncomingInventoryRequest request) {
		FieldValidator.notNull(request, "RegisterIncomingInventoryRequest");
	}
	
	private void checkIfQuantityIsGreaterThanZero(Integer quantity) {
		FieldValidator.notZeroOrNegative(quantity, "quantity");
	}
	
	private Movement convertToMovement(RegisterIncomingInventoryRequest request) {

		return Movement.of(
				request.getId(), 
				request.getProductId(), 
				MovementType.INCOMINGS, 
				request.getQuantity(), 
				request.getUnitPrice(), 
				request.getObservation(), 
				LocalDateTime.now()
		);
	}
	
}
