package coop.tecso.exam.todo1.hulkstore.domain.service.exceptions;

public class NotAValidMovementTypeException extends BusinessException {
	
	private static final long serialVersionUID = 1L;
	private static final String CODE = "NOT_A_VALID_MOVEMENT_TYPE";
	private static final String MESSAGE = "The movement type '%s' is unknown";
	
	public NotAValidMovementTypeException(String type) {
		super(CODE, String.format(MESSAGE, type));
	}

}
