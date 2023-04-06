package coop.tecso.exam.todo1.hulkstore.application.request;

import java.math.BigDecimal;

import coop.tecso.exam.todo1.hulkstore.domain.validator.FieldValidator;

public class RegisterIncomingInventoryRequest {
	
	private String id;
	
	private String productId;
	
	private Integer quantity;
	
	private BigDecimal unitPrice;
	
	private String observation;

	public RegisterIncomingInventoryRequest(String id, String productId, Integer quantity, BigDecimal unitPrice,
			String observation) {
		
		FieldValidator.notEmpty("id", id);
		FieldValidator.notEmpty("productId", productId);
		FieldValidator.notNull(quantity, "quantity");
		FieldValidator.notNull(unitPrice, "unitPrice");
		FieldValidator.notEmpty("observation", observation);
		
		FieldValidator.notNegative(unitPrice, "unitPrice");

		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.observation = observation;
	}

	public static RegisterIncomingInventoryRequest of(String id, String productId, Integer quantity, BigDecimal unitPrice,
			String observation) {
		return new RegisterIncomingInventoryRequest(id, productId, quantity, unitPrice, observation);
	}

	public String getId() {
		return id;
	}

	public String getProductId() {
		return productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public String getObservation() {
		return observation;
	}
	
}
