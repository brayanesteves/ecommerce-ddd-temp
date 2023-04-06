package coop.tecso.exam.todo1.hulkstore.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movements")

public class Movement {
	
	@Id
	private String id;
	
	@Column(name = "product_id")
	private String productId;
	
	@Enumerated(EnumType.STRING)
	private MovementType type;
	
	private Integer quantity;
	
	private BigDecimal price;
	
	private String observation;
	
	@Column(name = "created_at", columnDefinition = "TIMESTAMP")
	private LocalDateTime createdAt;
	
	public Movement() {
		super();
	}

	public Movement(String id, String productId, MovementType type, Integer quantity, BigDecimal price,
			String observation, LocalDateTime createdAt) {
		this.id = id;
		this.productId = productId;
		this.type = type;
		this.quantity = quantity;
		this.price = price;
		this.observation = observation;
		this.createdAt = createdAt;
	}
	
	public static Movement of(String id, String productId, MovementType type, Integer quantity, BigDecimal price,
			String observation, LocalDateTime createdAt) {
		return new Movement(id, productId, type, quantity, price, observation, createdAt);
	}

	public String getId() {
		return id;
	}

	public String getProductId() {
		return productId;
	}

	public MovementType getType() {
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
}
