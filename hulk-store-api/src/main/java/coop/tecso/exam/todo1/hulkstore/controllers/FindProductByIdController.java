package coop.tecso.exam.todo1.hulkstore.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.exam.todo1.hulkstore.application.dto.ProductDto;
import coop.tecso.exam.todo1.hulkstore.application.service.FindProductByIdService;

@RestController

public class FindProductByIdController {

	private FindProductByIdService service;
	
	public FindProductByIdController(FindProductByIdService service) {
		this.service = service;
	}

	@GetMapping("/api/products/{productId}")
	public ResponseEntity<ProductDto> handleRequest(@PathVariable String productId) {
		
		ProductDto product = service.execute(productId);
		
		return ResponseEntity.ok(product);
		
	}
	
}
