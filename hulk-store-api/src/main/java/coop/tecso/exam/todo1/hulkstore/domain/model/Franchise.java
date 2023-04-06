package coop.tecso.exam.todo1.hulkstore.domain.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "franchises")

public class Franchise {
	
	@Id
	private String id;
	
	private String name;
	
	public Franchise() {
		super();
	}

	public Franchise(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static Franchise of(String id, String name) {
		return new Franchise(id, name);
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object other) {
		
		if(!(other instanceof Franchise)) { return false; }
		
		Franchise otherFranchise = (Franchise) other;
		
		return getId().equals(otherFranchise.getId()) &&
			   getName().equals(otherFranchise.getName());
		
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName());
	}
	
}
