package coop.tecso.exam.todo1.hulkstore.controllers.response;

public class RegisterMovementResponse {
	
	private String movementId;

	public RegisterMovementResponse(String movementId) {
		this.movementId = movementId;
	}
	
	public static RegisterMovementResponse of(String movementId) {
		return new RegisterMovementResponse(movementId);
	}

	public String getMovementId() {
		return movementId;
	}

}
