package coop.tecso.exam.todo1.hulkstore.application.dto;

import java.math.BigDecimal;

import coop.tecso.exam.todo1.hulkstore.domain.model.Category;
import coop.tecso.exam.todo1.hulkstore.domain.model.Franchise;
import coop.tecso.exam.todo1.hulkstore.domain.model.Product;

public class ProductDto {
	
	private String id;
	
	private String code;
	
	private String name;
	
	private BigDecimal purchasePrice;
	
	private BigDecimal sellingPrice;
	
	private CategoryDto category;
	
	private FranchiseDto franchise;

	public ProductDto(String id, String code, String name, BigDecimal purchasePrice, BigDecimal sellingPrice,
			CategoryDto category, FranchiseDto franchise) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.purchasePrice = purchasePrice;
		this.sellingPrice = sellingPrice;
		this.category = category;
		this.franchise = franchise;
	}
	
	public static ProductDto of(String id, String code, String name, BigDecimal purchasePrice, BigDecimal sellingPrice,
			CategoryDto category, FranchiseDto franchise) {
		return new ProductDto(id, code, name, purchasePrice, sellingPrice, category, franchise);
	}
	
	public static ProductDto from(Product product, Category category, Franchise franchise) {
		CategoryDto categoryDto = CategoryDto.fromModel(category);
		FranchiseDto franchiseDto = FranchiseDto.fromModel(franchise);
		return of(product.getId(), product.getCode(), product.getName(), product.getPurchasePrice(), product.getSellingPrice(), 
				categoryDto, franchiseDto);
	}

	public String getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public FranchiseDto getFranchise() {
		return franchise;
	}

}
