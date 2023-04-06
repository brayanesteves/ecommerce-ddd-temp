package coop.tecso.exam.todo1.hulkstore.application.dto;

public class InventoryInfoDto {
	
	private String productId;
	
	private Integer incomings;
	
	private Integer outgoings;

	private InventoryInfoDto(String productId, Integer incomings, Integer outgoings) {
		this.productId = productId;
		this.incomings = incomings;
		this.outgoings = outgoings;
	}
	
	public static InventoryInfoDto of(String productId, Integer incomings, Integer outgoings) {
		return new InventoryInfoDto(productId, incomings, outgoings);
	}

	public String getProductId() {
		return productId;
	}

	public Integer getIncomings() {
		return incomings;
	}

	public Integer getOutgoings() {
		return outgoings;
	}

	public Integer getAvailableItems() {
		return incomings - outgoings;
	}
	
}
