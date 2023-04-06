package coop.tecso.exam.todo1.hulkstore.controllers.response;

import java.util.List;

import coop.tecso.exam.todo1.hulkstore.application.dto.ProductDto;

public class FindAllProductsResponse {
	
	private List<ProductDto> products;

	private FindAllProductsResponse(List<ProductDto> products) {
		this.products = products;
	}
	
	public static FindAllProductsResponse of(List<ProductDto> products) {
		return new FindAllProductsResponse(products);
	}

	public List<ProductDto> getProducts() {
		return products;
	}

}
