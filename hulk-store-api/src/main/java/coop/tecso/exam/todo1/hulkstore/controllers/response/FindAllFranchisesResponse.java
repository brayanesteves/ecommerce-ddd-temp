package coop.tecso.exam.todo1.hulkstore.controllers.response;

import java.util.List;

import coop.tecso.exam.todo1.hulkstore.application.dto.FranchiseDto;

public class FindAllFranchisesResponse {
	
	private List<FranchiseDto> franchises;

	public FindAllFranchisesResponse(List<FranchiseDto> franchises) {
		this.franchises = franchises;
	}
	
	public static FindAllFranchisesResponse of(List<FranchiseDto> franchises) {
		return new FindAllFranchisesResponse(franchises);
	}

	public List<FranchiseDto> getFranchises() {
		return franchises;
	}

}
