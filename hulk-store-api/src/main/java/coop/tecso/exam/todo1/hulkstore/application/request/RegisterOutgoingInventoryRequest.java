package coop.tecso.exam.todo1.hulkstore.application.request;

import java.math.BigDecimal;

public class RegisterOutgoingInventoryRequest extends RegisterIncomingInventoryRequest {

	public RegisterOutgoingInventoryRequest(String id, String productId, Integer quantity, BigDecimal unitPrice,
			String observation) {
		super(id, productId, quantity, unitPrice, observation);
	}
	
	public static RegisterOutgoingInventoryRequest of(String id, String productId, Integer quantity, BigDecimal unitPrice,
			String observation) {
		return new RegisterOutgoingInventoryRequest(id, productId, quantity, unitPrice, observation);
	}

}
