package coop.tecso.exam.todo1.hulkstore.controllers.response;

public class LoginResponse {
	
	private String token;

	private LoginResponse(String token) {
		this.token = token;
	}
	
	public static LoginResponse of(String token) {
		return new LoginResponse(token);
	}

	public String getToken() {
		return token;
	}

}
