package coop.tecso.exam.todo1.hulkstore.domain.validator;

public class InvalidFieldException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidFieldException(String message) {
		super(message);
	}

}
