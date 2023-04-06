package coop.tecso.exam.todo1.hulkstore.controllers.response;

import java.util.List;

import coop.tecso.exam.todo1.hulkstore.application.dto.InventoryInfoDto;

public class GetInventoryInfoResponse {
	
	private List<InventoryInfoDto> items;

	private GetInventoryInfoResponse(List<InventoryInfoDto> items) {
		super();
		this.items = items;
	}
	
	public static GetInventoryInfoResponse of(List<InventoryInfoDto> items) {
		return new GetInventoryInfoResponse(items);
	}

	public List<InventoryInfoDto> getItems() {
		return items;
	}
	
}
