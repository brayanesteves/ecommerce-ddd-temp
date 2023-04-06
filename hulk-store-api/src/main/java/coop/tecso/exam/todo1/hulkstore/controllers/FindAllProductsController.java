package coop.tecso.exam.todo1.hulkstore.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.exam.todo1.hulkstore.application.dto.ProductDto;
import coop.tecso.exam.todo1.hulkstore.application.request.FilterOptions;
import coop.tecso.exam.todo1.hulkstore.application.service.FindAllProductsService;
import coop.tecso.exam.todo1.hulkstore.controllers.response.FindAllProductsResponse;

@RestController

public class FindAllProductsController {

	private FindAllProductsService service;

	public FindAllProductsController(FindAllProductsService service) {
		this.service = service;
	}

	@GetMapping("/api/products")
	public ResponseEntity<FindAllProductsResponse> handleRequest(
		@RequestParam(required = false) String franchiseId,
		@RequestParam(required = false) String categoryId
	) {
		
		FilterOptions filter = new FilterOptions();
		filter.setFranchiseId(franchiseId);
		filter.setCategoryId(categoryId);
		
		List<ProductDto> allProducts = service.execute(filter);
		
		FindAllProductsResponse responseBody = FindAllProductsResponse.of(allProducts);
		
		return ResponseEntity.ok(responseBody);
		
	}
	
}
