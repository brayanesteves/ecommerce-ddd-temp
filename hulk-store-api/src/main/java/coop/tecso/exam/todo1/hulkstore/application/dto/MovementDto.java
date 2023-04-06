package coop.tecso.exam.todo1.hulkstore.application.dto;

import java.math.BigDecimal;

import coop.tecso.exam.todo1.hulkstore.domain.model.Movement;
import coop.tecso.exam.todo1.hulkstore.util.DateUtil;

public class MovementDto {
	
	private String id;
	
	private String productId;
	
	private String type;
	
	private Integer quantity;
	
	private BigDecimal price;
	
	private String observation;
	
	private String createdAt;

	public MovementDto(String id, String productId, String type, Integer quantity, BigDecimal price, String observation,
			String createdAt) {
		this.id = id;
		this.productId = productId;
		this.type = type;
		this.quantity = quantity;
		this.price = price;
		this.observation = observation;
		this.createdAt = createdAt;
	}
	
	public static MovementDto of(String id, String productId, String type, Integer quantity, BigDecimal price, String observation,
			String createdAt) {
		return new MovementDto(id, productId, type, quantity, price, observation, createdAt);
	}
	
	public static MovementDto fromModel(Movement model) {
		return new MovementDto(model.getId(), model.getProductId(), model.getType().name(), model.getQuantity(), 
				model.getPrice(), model.getObservation(), DateUtil.convert(model.getCreatedAt()));
	}

	public String getId() {
		return id;
	}

	public String getProductId() {
		return productId;
	}

	public String getType() {
		return type;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getObservation() {
		return observation;
	}

	public String getCreatedAt() {
		return createdAt;
	}

}
