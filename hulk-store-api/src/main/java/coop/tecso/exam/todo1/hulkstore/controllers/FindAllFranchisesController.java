package coop.tecso.exam.todo1.hulkstore.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.exam.todo1.hulkstore.application.dto.FranchiseDto;
import coop.tecso.exam.todo1.hulkstore.application.service.FindAllFranchisesService;
import coop.tecso.exam.todo1.hulkstore.controllers.response.FindAllFranchisesResponse;

@RestController

public class FindAllFranchisesController {

	private FindAllFranchisesService service;
	
	public FindAllFranchisesController(FindAllFranchisesService service) {
		this.service = service;
	}

	@GetMapping("/api/franchises")
	public ResponseEntity<FindAllFranchisesResponse> handleRequest() {
		
		List<FranchiseDto> allFranchises = service.execute();
		
		FindAllFranchisesResponse responseBody = FindAllFranchisesResponse.of(allFranchises);
		
		return ResponseEntity.ok(responseBody);
		
	}
	
}
