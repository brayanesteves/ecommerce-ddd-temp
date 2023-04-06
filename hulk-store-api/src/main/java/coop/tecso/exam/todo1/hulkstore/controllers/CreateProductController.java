package coop.tecso.exam.todo1.hulkstore.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.exam.todo1.hulkstore.application.request.CreateProductRequest;
import coop.tecso.exam.todo1.hulkstore.application.service.CreateProductService;
import coop.tecso.exam.todo1.hulkstore.controllers.request.CreateProductHttpRequest;
import coop.tecso.exam.todo1.hulkstore.controllers.response.CreateProductResponse;

@RestController

public class CreateProductController {

	private CreateProductService service;

	public CreateProductController(CreateProductService service) {
		this.service = service;
	}
	
	@PostMapping("/api/products")
	public ResponseEntity<CreateProductResponse> handleRequest(
			@RequestBody CreateProductHttpRequest requestBody 
	) {
		
		CreateProductRequest request = toServiceRequest(requestBody);
		
		service.execute(request);
		
		CreateProductResponse responseBody = CreateProductResponse.of(request.getId());
		
		return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
		
	}
	
	public CreateProductRequest toServiceRequest(CreateProductHttpRequest requestBody) {
		CreateProductRequest request = new CreateProductRequest();
		request.setId(UUID.randomUUID().toString());
		request.setCode(requestBody.getCode());
		request.setName(requestBody.getName());
		request.setPurchasePrice(requestBody.getPurchasePrice());
		request.setSellingPrice(requestBody.getSellingPrice());
		request.setCategoryId(requestBody.getCategoryId());
		request.setFranchiseId(requestBody.getFranchiseId());
		return request;
	}

}
