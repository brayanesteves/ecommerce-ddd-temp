package coop.tecso.exam.todo1.hulkstore.application.request;

import coop.tecso.exam.todo1.hulkstore.domain.validator.FieldValidator;

public class CreateUserRequest {

	private String id;
	
	private String username;
	
	private String password;
	
	private String firstName;
	
	private String lastName;

	private CreateUserRequest(String id, String username, String password, String firstName, String lastName) {
		
		FieldValidator.notEmpty(id, "id");
		FieldValidator.notEmpty(username, "username");
		FieldValidator.notEmpty(password, "password");
		FieldValidator.notEmpty(firstName, "firstName");
		FieldValidator.notEmpty(lastName, "lastName");
		
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public static CreateUserRequest of(String id, String username, String password, String firstName, String lastName) {
		return new CreateUserRequest(id, username, password, firstName, lastName);
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

}
