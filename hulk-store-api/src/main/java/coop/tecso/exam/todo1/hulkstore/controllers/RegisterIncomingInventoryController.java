package coop.tecso.exam.todo1.hulkstore.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.exam.todo1.hulkstore.application.request.RegisterIncomingInventoryRequest;
import coop.tecso.exam.todo1.hulkstore.application.service.RegisterIncomingInventoryService;
import coop.tecso.exam.todo1.hulkstore.controllers.request.RegisterIncomingInventoryHttpRequest;
import coop.tecso.exam.todo1.hulkstore.controllers.response.RegisterMovementResponse;

@RestController

public class RegisterIncomingInventoryController {
	
	private RegisterIncomingInventoryService service;

	public RegisterIncomingInventoryController(RegisterIncomingInventoryService service) {
		this.service = service;
	}
	
	@PostMapping("/api/movements/{productId}/incomings")
	public ResponseEntity<RegisterMovementResponse> hanldeRequest(@PathVariable String productId, @RequestBody RegisterIncomingInventoryHttpRequest requestBody) {
		
		RegisterIncomingInventoryRequest request = toRequest(productId, requestBody);
		
		service.execute(request);
		
		RegisterMovementResponse responseBody = RegisterMovementResponse.of(request.getId());
		
		return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
		
	}
	
	private RegisterIncomingInventoryRequest toRequest(String productId, RegisterIncomingInventoryHttpRequest requestBody) {
		return RegisterIncomingInventoryRequest.of(
					UUID.randomUUID().toString(),
					productId,
					requestBody.getQuantity(), 
					requestBody.getUnitPrice(), 
					requestBody.getObservation()
		);
	}

}
