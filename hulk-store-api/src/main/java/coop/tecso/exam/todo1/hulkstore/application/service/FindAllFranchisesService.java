package coop.tecso.exam.todo1.hulkstore.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import coop.tecso.exam.todo1.hulkstore.application.dto.FranchiseDto;
import coop.tecso.exam.todo1.hulkstore.domain.service.FranchiseService;

/**
 * Use case: find all franchises, like Marvel, DC and others.
 * @author Cristian
 *
 */

@Service

public class FindAllFranchisesService {
	
	private FranchiseService franchiseService;
	
	public FindAllFranchisesService(FranchiseService franchiseService) {
		this.franchiseService = franchiseService;
	}

	public List<FranchiseDto> execute() {
		
		return franchiseService.findAllFranchises()
		                       .stream()
		                       .map(FranchiseDto::fromModel)
		                       .collect(Collectors.toList());
		
	}

}
