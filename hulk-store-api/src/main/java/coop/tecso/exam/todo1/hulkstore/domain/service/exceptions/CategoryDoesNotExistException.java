package coop.tecso.exam.todo1.hulkstore.domain.service.exceptions;

public class CategoryDoesNotExistException extends BusinessException {

	private static final long serialVersionUID = 1L;
	private static final String CODE = "CATEGORY_DOES_NOT_EXIST";
	private static final String MESSAGE = "Category '%s' does not exist";
	
	public CategoryDoesNotExistException(String id) {
		super(CODE, String.format(MESSAGE, id));
	}
	
}
