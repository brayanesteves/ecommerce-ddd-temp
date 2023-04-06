package coop.tecso.exam.todo1.hulkstore.domain.service.exceptions;

public class UsernameAlreadyExistsException extends BusinessException {
	
	private static final long serialVersionUID = 1L;
	private static final String CODE = "USERNAME_ALREADY_EXISTS";
	private static final String MESSAGE = "Username '%s' is already taken";

	public UsernameAlreadyExistsException(String username) {
		super(CODE, String.format(MESSAGE, username));
	}

}
