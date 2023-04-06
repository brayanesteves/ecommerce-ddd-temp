package coop.tecso.exam.todo1.hulkstore.application.dto;

import coop.tecso.exam.todo1.hulkstore.domain.model.Franchise;

public class FranchiseDto {
	
	private String id;
	
	private String name;
	
	public FranchiseDto(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static FranchiseDto of(String id, String name) {
		return new FranchiseDto(id, name);
	}
	
	public static FranchiseDto fromModel(Franchise model) {
		return of(model.getId(), model.getName());
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
