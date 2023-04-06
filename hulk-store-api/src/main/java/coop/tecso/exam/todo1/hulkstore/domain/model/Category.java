package coop.tecso.exam.todo1.hulkstore.domain.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categories")

public class Category {
	
	@Id
	private String id;
	
	private String name;
	
	public Category() {
		super();
	}
	
	public Category(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static Category of(String id, String name) {
		return new Category(id, name);
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object other) {
		
		if(!(other instanceof Category)) { return false; }
		
		Category otherCategory = (Category) other;
		
		return getId().equals(otherCategory.getId()) &&
			   getName().equals(otherCategory.getName());
		
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName());
	}
	
}
