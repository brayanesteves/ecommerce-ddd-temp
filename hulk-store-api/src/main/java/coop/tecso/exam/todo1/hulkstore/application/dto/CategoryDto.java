package coop.tecso.exam.todo1.hulkstore.application.dto;

import coop.tecso.exam.todo1.hulkstore.domain.model.Category;

public class CategoryDto {
	
	private String id;
	
	private String name;
	
	public CategoryDto(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static CategoryDto of(String id, String name) {
		return new CategoryDto(id, name);
	}
	
	public static CategoryDto fromModel(Category model) {
		return of(model.getId(), model.getName());
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
