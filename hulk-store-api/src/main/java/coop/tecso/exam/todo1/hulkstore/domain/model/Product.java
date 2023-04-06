package coop.tecso.exam.todo1.hulkstore.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import coop.tecso.exam.todo1.hulkstore.domain.validator.FieldValidator;

@Entity
@Table(name = "products")

public class Product {

	@Id
	private String id;
	
	private String code;
	
	private String name;
	
	@Column(name = "purchase_price")
	private BigDecimal purchasePrice;
	
	@Column(name = "selling_price")
	private BigDecimal sellingPrice;
	
	@Column(name = "category_id")
	private String categoryId;
	
	@Column(name = "franchise_id")
	private String franchiseId;

	public Product() {
	}
	
	public Product(String id, String code, String name, BigDecimal purchasePrice, BigDecimal sellingPrice, String categoryId, String franchiseId) {
		
		FieldValidator.notEmpty(id, "id");
		FieldValidator.notEmpty(code, "code");
		FieldValidator.notEmpty(name, "name");
		FieldValidator.notNull(purchasePrice, "purchasePrice");
		FieldValidator.notNull(sellingPrice, "sellingPrice");
		FieldValidator.notEmpty(categoryId, "categoryId");
		FieldValidator.notEmpty(franchiseId, "franchiseId");
		
		FieldValidator.notZeroOrNegative(purchasePrice, "purchasePrice");
		FieldValidator.notZeroOrNegative(sellingPrice, "sellingPrice");
		
		this.id = id;
		this.code = code;
		this.name = name;
		this.categoryId = categoryId;
		this.franchiseId = franchiseId;
		this.purchasePrice = purchasePrice;
		this.sellingPrice = sellingPrice;
	}
	
	public static Product of(String id, String code, String name, BigDecimal purchasePrice, BigDecimal sellingPrice, String categoryId, String franchiseId) {
		return new Product(id, code, name, purchasePrice, sellingPrice, categoryId, franchiseId);
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

	public String getCategoryId() {
		return categoryId;
	}

	public String getFranchiseId() {
		return franchiseId;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	@Override
	public boolean equals(Object other) {
		
		if(!(other instanceof Product)) { return false; }
		
		Product otherProduct = (Product) other;
		
		return getId().equals(otherProduct.getId()) &&
			   getCode().equals(otherProduct.getCode()) &&
			   getName().equals(otherProduct.getName()) &&
			   getCategoryId().equals(otherProduct.getCategoryId()) &&
			   getFranchiseId().equals(otherProduct.getFranchiseId()) &&
			   getPurchasePrice().equals(otherProduct.getPurchasePrice()) &&
			   getSellingPrice().equals(otherProduct.getSellingPrice());
		
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getId(), getCode(), getName(), getCategoryId(), getFranchiseId(), getPurchasePrice(), getSellingPrice());
	}
	
}
