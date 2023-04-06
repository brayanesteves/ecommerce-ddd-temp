package coop.tecso.exam.todo1.hulkstore.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.exam.todo1.hulkstore.application.request.RegisterOutgoingInventoryRequest;
import coop.tecso.exam.todo1.hulkstore.application.service.RegisterOutgoingInventoryService;
import coop.tecso.exam.todo1.hulkstore.controllers.request.RegisterOutgoingInventoryHttpRequest;
import coop.tecso.exam.todo1.hulkstore.controllers.response.RegisterMovementResponse;

@RestController

public class RegisterOutgoingInventoryController {

	private RegisterOutgoingInventoryService service;
	
	public RegisterOutgoingInventoryController(RegisterOutgoingInventoryService service) {
		this.service = service;
	}

	@PostMapping("/api/movements/{productId}/outgoings")
	public ResponseEntity<RegisterMovementResponse> handleRequest(@PathVariable String productId, @RequestBody RegisterOutgoingInventoryHttpRequest requestBody) {
		
		RegisterOutgoingInventoryRequest request = toRequest(productId, requestBody);
		
		service.execute(request);
		
		RegisterMovementResponse responseBody = RegisterMovementResponse.of(request.getId());
		
		return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
		
	}
	
	private RegisterOutgoingInventoryRequest toRequest(String productId, RegisterOutgoingInventoryHttpRequest requestBody) {
		return RegisterOutgoingInventoryRequest.of(
					UUID.randomUUID().toString(),
					productId,
					requestBody.getQuantity(), 
					requestBody.getUnitPrice(), 
					requestBody.getObservation()
		);
	}
	
}
