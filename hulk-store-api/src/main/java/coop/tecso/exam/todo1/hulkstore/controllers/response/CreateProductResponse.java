package coop.tecso.exam.todo1.hulkstore.controllers.response;

public class CreateProductResponse {

	private String productId;

	public CreateProductResponse(String productId) {
		this.productId = productId;
	}
	
	public static CreateProductResponse of(String productId) {
		return new CreateProductResponse(productId);
	}

	public String getProductId() {
		return productId;
	}

}
