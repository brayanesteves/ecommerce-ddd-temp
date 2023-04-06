package coop.tecso.exam.todo1.hulkstore.application.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import coop.tecso.exam.todo1.hulkstore.application.request.RegisterIncomingInventoryRequest;
import coop.tecso.exam.todo1.hulkstore.application.request.RegisterOutgoingInventoryRequest;
import coop.tecso.exam.todo1.hulkstore.domain.model.Category;
import coop.tecso.exam.todo1.hulkstore.domain.model.Franchise;
import coop.tecso.exam.todo1.hulkstore.domain.model.Movement;
import coop.tecso.exam.todo1.hulkstore.domain.model.MovementType;
import coop.tecso.exam.todo1.hulkstore.domain.model.Product;
import coop.tecso.exam.todo1.hulkstore.domain.repository.MovementRepository;
import coop.tecso.exam.todo1.hulkstore.domain.repository.ProductRepository;
import coop.tecso.exam.todo1.hulkstore.domain.service.MovementService;
import coop.tecso.exam.todo1.hulkstore.domain.service.ProductService;
import coop.tecso.exam.todo1.hulkstore.domain.service.exceptions.ProductDoesNotExistException;
import coop.tecso.exam.todo1.hulkstore.domain.service.exceptions.ProductWithoutEnoughStockException;
import coop.tecso.exam.todo1.hulkstore.domain.validator.InvalidFieldException;

@ExtendWith(MockitoExtension.class)

final class RegisterOutgoingInventoryServiceTests {

	@Mock
	private MovementRepository movementRepository;
	
	@Mock
	private ProductRepository productRepository;
	
	private MovementService movementService;
	
	private ProductService productService;
	
	private RegisterOutgoingInventoryService service;
	
	@BeforeEach
	public void setUp() {
		movementService = new MovementService(movementRepository);
		productService = new ProductService(productRepository);
		service = new RegisterOutgoingInventoryService(movementService, productService);
	}
	
	@Test
	@DisplayName("Cannot allow to invoke service with a null parameter")
	void cannotInvokeServiceWithNullParameter() {
		
		assertThrows(InvalidFieldException.class, () -> service.execute(null) );
		assertThrows(InvalidFieldException.class, () -> new RegisterOutgoingInventoryRequest(null, null, null, null, null) );
		
	}
	
	@Test
	@DisplayName("Cannot create the movement if the quantity is zero or below")
	void cannotCreateMovementWithQuantityZero() {
		
		String movementId = "5d364b17-bf10-4b35-9aff-adfebf04b8eb";
		String productId = "a5300e96-2968-467c-9f54-79eb0bedc94d";
		Integer quantity = 0;
		BigDecimal unitPrice = new BigDecimal("5");
		
		RegisterIncomingInventoryRequest request = new RegisterIncomingInventoryRequest(movementId, productId, quantity, unitPrice, "");
		
		assertThrows(InvalidFieldException.class, () -> service.execute(request));
		
	}
	
	@Test
	@DisplayName("Cannot create a movement if the Product Id is unknown")
	void cannotCreateMovementForAnUnknwonProduct() {
		
		String movementId = "5d364b17-bf10-4b35-9aff-adfebf04b8eb";
		String randomProductId = "a5300e96-2968-467c-9f54-79eb0bedc94d";
		Integer quantity = 1;
		BigDecimal unitPrice = new BigDecimal("5");
		RegisterOutgoingInventoryRequest request = new RegisterOutgoingInventoryRequest(movementId, randomProductId, quantity, unitPrice, "");
		
		Mockito.when(productRepository.findById(randomProductId)).thenReturn(Optional.empty());
		
		assertThrows(ProductDoesNotExistException.class, () -> service.execute(request) );
		
	}

	@Test
	@DisplayName("Cannot create the movement if the product does not have any incoming movement first")
	void cannotCreateMovementIfHasNoIncomingMovements() {
		
		String movementId = "5d364b17-bf10-4b35-9aff-adfebf04b8eb";
		String productId = "a5300e96-2968-467c-9f54-79eb0bedc94d";
		Integer quantity = 10;
		BigDecimal unitPrice = new BigDecimal("5");
		
		RegisterOutgoingInventoryRequest request = new RegisterOutgoingInventoryRequest(movementId, productId, quantity, unitPrice, "");
		
		mockProduct(productId);
		
		Mockito.when(movementRepository.findByProductId(productId)).thenReturn(Collections.emptyList());

		assertThrows(ProductWithoutEnoughStockException.class, () -> service.execute(request));
		
	}
	
	@Test
	@DisplayName("Cannot create the movement if the product does not have enough stock")
	void cannotCreateMovementIfProductDoesntHaveEnoughStock() {
		
		String productId = "a5300e96-2968-467c-9f54-79eb0bedc94d";
		mockProduct(productId);
		mockMovements(productId);
		
		String movementId = "5d364b17-bf10-4b35-9aff-adfebf04b8eb";
		Integer quantity = 1000;
		BigDecimal unitPrice = new BigDecimal("5000");
		
		RegisterOutgoingInventoryRequest request = new RegisterOutgoingInventoryRequest(movementId, productId, quantity, unitPrice, "");
		
		assertThrows(ProductWithoutEnoughStockException.class, () -> service.execute(request));
		
	}
	
	@Test
	@DisplayName("Create a valid outgoing movement")
	void shouldCreateAValidOutgoingMovement() {
		
		String productId = "a5300e96-2968-467c-9f54-79eb0bedc94d";
		mockProduct(productId);
		mockMovements(productId);
		
		String movementId = "5d364b17-bf10-4b35-9aff-adfebf04b8eb";
		Integer quantity = 4;
		BigDecimal unitPrice = new BigDecimal("5000");
		
		RegisterOutgoingInventoryRequest request = new RegisterOutgoingInventoryRequest(movementId, productId, quantity, unitPrice, "");
		
		assertDoesNotThrow(() -> service.execute(request));
		
	}
	
	private void mockProduct(String productId) {
		Category category = Category.of("f3559fb4-ea4a-4c86-b889-e0838a0719c5", "T-shirts");
		Franchise franchise = Franchise.of("9878cdc6-d089-405f-9f4d-5d53dcc79726", "Marvel");
		Product product = Product.of(productId, "001", "Product 1", new BigDecimal("100"), new BigDecimal("200"), category.getId(), franchise.getId());
		Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(product));
	}
	
	private void mockMovements(String productId) {
		
		String id = "c26907bb-adf4-4160-96e0-20545a3543ef";
		MovementType type = MovementType.INCOMINGS;
		Integer quantity = 10;
		BigDecimal price = new BigDecimal("3000");
		String observation = "Some observation";
		LocalDateTime createdAt = LocalDateTime.now();
		Movement movement = Movement.of(id, productId, type, quantity, price, observation, createdAt);
		
		List<Movement> allMovements = Arrays.asList(movement);
		
		Mockito.when(movementRepository.findByProductId(productId)).thenReturn(allMovements);
		
	}
	
}
