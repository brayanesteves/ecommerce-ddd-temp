package coop.tecso.exam.todo1.hulkstore.application.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import coop.tecso.exam.todo1.hulkstore.application.dto.MovementDto;
import coop.tecso.exam.todo1.hulkstore.domain.model.Movement;
import coop.tecso.exam.todo1.hulkstore.domain.service.MovementService;

@Service

public class FindAllMovementsService {

	private MovementService movementService;

	public FindAllMovementsService(MovementService movementService) {
		this.movementService = movementService;
	}
	
	public List<MovementDto> execute(String productId) {
		
		List<Movement> allMovements = productId != null ? movementService.findAllMovementsByProduct(productId)
				                                        : movementService.findAllMovements();
		
		return allMovements.stream()
				           .sorted(Comparator.comparing(Movement::getCreatedAt))
				           .map(MovementDto::fromModel)
				           .collect(Collectors.toList());
		
	}
	
}
