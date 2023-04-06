package coop.tecso.exam.todo1.hulkstore.domain.service.exceptions;

public class ProductDoesNotExistException extends BusinessException {

	private static final long serialVersionUID = 1L;
	private static final String CODE = "PRODUCT_DOES_NOT_EXIST";
	private static final String MESSAGE = "Product '%s' does not exist";
	
	public ProductDoesNotExistException(String id) {
		super(CODE, String.format(MESSAGE, id));
	}
	
}
