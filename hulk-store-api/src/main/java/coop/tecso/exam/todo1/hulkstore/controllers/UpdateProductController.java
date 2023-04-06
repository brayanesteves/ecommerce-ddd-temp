package coop.tecso.exam.todo1.hulkstore.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.exam.todo1.hulkstore.application.request.UpdateProductRequest;
import coop.tecso.exam.todo1.hulkstore.application.service.UpdateProductService;
import coop.tecso.exam.todo1.hulkstore.controllers.request.UpdateProductHttpRequest;

@RestController

public class UpdateProductController {

	private UpdateProductService service;
	
	public UpdateProductController(UpdateProductService service) {
		this.service = service;
	}

	@PutMapping("/api/products/{productId}")
	public ResponseEntity<Void> handleRequest(
			@PathVariable String productId,
			@RequestBody UpdateProductHttpRequest requestBody
	) {
		
		UpdateProductRequest request = toServiceRequest(productId, requestBody);
		
		service.execute(request);
		
		return ResponseEntity.noContent().build();
		
	}

	public UpdateProductRequest toServiceRequest(String productId, UpdateProductHttpRequest requestBody) {
		UpdateProductRequest request = new UpdateProductRequest();
		request.setId(productId);
		request.setCode(requestBody.getCode());
		request.setName(requestBody.getName());
		request.setPurchasePrice(requestBody.getPurchasePrice());
		request.setSellingPrice(requestBody.getSellingPrice());
		request.setCategoryId(requestBody.getCategoryId());
		request.setFranchiseId(requestBody.getFranchiseId());
		return request;
	}
	
}
