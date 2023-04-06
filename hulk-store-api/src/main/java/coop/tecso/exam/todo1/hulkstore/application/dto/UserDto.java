package coop.tecso.exam.todo1.hulkstore.application.dto;

import coop.tecso.exam.todo1.hulkstore.domain.model.User;

public class UserDto {
	
	private String id;
	
	private String username;

	private String firstName;
	
	private String lastName;

	private UserDto(String id, String username, String firstName, String lastName) {
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public static UserDto of(String id, String username, String password, String firstName, String lastName) {
		return new UserDto(id, username, firstName, lastName);
	}
	
	public static UserDto fromModel(User user) {
		return new UserDto(
				user.getId(), 
				user.getUsername(), 
				user.getFirstName(), 
				user.getLastName()
		);
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

}
