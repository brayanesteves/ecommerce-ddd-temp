package coop.tecso.exam.todo1.hulkstore.domain.service.exceptions;

public class UserDoesNotExistException extends BusinessException {

	private static final long serialVersionUID = 1L;
	private static final String CODE = "USER_DOES_NOT_EXIST";
	private static final String MESSAGE = "User '%s' does not exist";
	
	public UserDoesNotExistException(String idOrUsername) {
		super(CODE, String.format(MESSAGE, idOrUsername));
	}
	
}
