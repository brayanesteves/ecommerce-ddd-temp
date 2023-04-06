package coop.tecso.exam.todo1.hulkstore.domain.service.exceptions;

public class ProductCodeAlreadyExistsException extends BusinessException {

	private static final long serialVersionUID = 1L;
	private static final String CODE = "PRODUCT_CODE_ALREADY_EXISTS";
	private static final String MESSAGE = "Product with code '%s' is already registered";
	
	public ProductCodeAlreadyExistsException(String code) {
		super(CODE, String.format(MESSAGE, code));
	}
	
}
