package coop.tecso.exam.todo1.hulkstore.domain.service.exceptions;

public class FranchiseDoesNotExistException extends BusinessException {

	private static final long serialVersionUID = 1L;
	private static final String CODE = "FRANCHISE_DOES_NOT_EXIST";
	private static final String MESSAGE = "Franchise '%s' does not exist";
	
	public FranchiseDoesNotExistException(String id) {
		super(CODE, String.format(MESSAGE, id));
	}
	
}
