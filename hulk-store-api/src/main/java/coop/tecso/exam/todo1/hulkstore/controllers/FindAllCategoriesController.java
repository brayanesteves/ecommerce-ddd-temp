package coop.tecso.exam.todo1.hulkstore.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.exam.todo1.hulkstore.application.dto.CategoryDto;
import coop.tecso.exam.todo1.hulkstore.application.service.FindAllCategoriesService;
import coop.tecso.exam.todo1.hulkstore.controllers.response.FindAllCategoriesResponse;

@RestController

public class FindAllCategoriesController {

	private FindAllCategoriesService service;
	
	public FindAllCategoriesController(FindAllCategoriesService service) {
		this.service = service;
	}

	@GetMapping("/api/categories")
	public ResponseEntity<FindAllCategoriesResponse> handleRequest() {
		
		List<CategoryDto> allCategories = service.execute();
		
		FindAllCategoriesResponse responseBody = FindAllCategoriesResponse.of(allCategories);
		
		return ResponseEntity.ok(responseBody);
		
	}
	
}
