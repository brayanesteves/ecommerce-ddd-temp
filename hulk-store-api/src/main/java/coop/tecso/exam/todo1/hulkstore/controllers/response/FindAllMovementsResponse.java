package coop.tecso.exam.todo1.hulkstore.controllers.response;

import java.util.List;

import coop.tecso.exam.todo1.hulkstore.application.dto.MovementDto;

public class FindAllMovementsResponse {
	
	private List<MovementDto> movements;

	public FindAllMovementsResponse(List<MovementDto> movements) {
		this.movements = movements;
	}
	
	public static FindAllMovementsResponse of(List<MovementDto> movements) {
		return new FindAllMovementsResponse(movements);
	}

	public List<MovementDto> getMovements() {
		return movements;
	}

}
