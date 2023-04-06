package coop.tecso.exam.todo1.hulkstore.domain.service.exceptions;

public class ProductWithoutEnoughStockException extends BusinessException {

	private static final long serialVersionUID = 1L;
	private static final String CODE = "PRODUCT_WITHOUT_STOCK";
	private static final String MESSAGE = "Cannot make this movement because product '%s' does not have enough stock";
	
	public ProductWithoutEnoughStockException(String productId) {
		super(CODE, String.format(MESSAGE, productId));
	}
	
}
