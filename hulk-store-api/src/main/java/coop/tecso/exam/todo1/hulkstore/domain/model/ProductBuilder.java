package coop.tecso.exam.todo1.hulkstore.domain.model;

import java.math.BigDecimal;

public class ProductBuilder {
	
	private String id;
	
	private String code;
	
	private String name;
	
	private BigDecimal purchasePrice;
	
	private BigDecimal sellingPrice;
	
	private String categoryId;
	
	private String franchiseId;
	
	private ProductBuilder() {
	}
	
	public static ProductBuilder newInstance() {
		return new ProductBuilder();
	}
	
	public ProductBuilder id(String id) {
		this.id = id;
		return this;
	}
	
	public ProductBuilder code(String code) {
		this.code = code;
		return this;
	}
	
	public ProductBuilder name(String name) {
		this.name = name;
		return this;
	}
	
	public ProductBuilder categoryId(String categoryId) {
		this.categoryId = categoryId;
		return this;
	}
	
	public ProductBuilder franchiseId(String franchiseId) {
		this.franchiseId = franchiseId;
		return this;
	}
	
	public ProductBuilder purchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
		return this;
	}
	
	public ProductBuilder sellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
		return this;
	}

	public Product build() {
		return Product.of(id, code, name, purchasePrice, sellingPrice, categoryId, franchiseId);
	}

}
