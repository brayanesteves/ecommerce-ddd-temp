package coop.tecso.exam.todo1.hulkstore.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import coop.tecso.exam.todo1.hulkstore.application.dto.InventoryInfoDto;
import coop.tecso.exam.todo1.hulkstore.application.request.FilterOptions;
import coop.tecso.exam.todo1.hulkstore.domain.model.Category;
import coop.tecso.exam.todo1.hulkstore.domain.model.Franchise;
import coop.tecso.exam.todo1.hulkstore.domain.model.Movement;
import coop.tecso.exam.todo1.hulkstore.domain.model.MovementType;
import coop.tecso.exam.todo1.hulkstore.domain.model.Product;
import coop.tecso.exam.todo1.hulkstore.domain.repository.MovementRepository;
import coop.tecso.exam.todo1.hulkstore.domain.repository.ProductRepository;
import coop.tecso.exam.todo1.hulkstore.domain.service.MovementService;
import coop.tecso.exam.todo1.hulkstore.domain.service.ProductService;

@ExtendWith(MockitoExtension.class)

final class GetInventoryInfoServiceTests {

	@Mock
	private MovementRepository movementRepository;
	
	@Mock
	private ProductRepository productRepository;
	
	private MovementService movementService;
	
	private ProductService productService;
	
	private GetInventoryInfoService service;
	
	@BeforeEach
	public void setUp() {
		movementService = new MovementService(movementRepository);
		productService = new ProductService(productRepository);
		service = new GetInventoryInfoService(movementService, productService);
	}
	
	@Test
	@DisplayName("If there are not products should get no info")
	void shouldGetNoInfo() {
		
		Mockito.when(productRepository.findAll()).thenReturn(Collections.emptyList());
		
		List<InventoryInfoDto> items = service.execute(noFilter());
		
		assertEquals(0, items.size());
		
	}
	
	@Test
	@DisplayName("Should summarize movements and get total quantities on inventory")
	void shouldGetSomeInfo() {
		
		String productId = "a5300e96-2968-467c-9f54-79eb0bedc94d";
		mockProducts(productId);
		mockMovements(productId);
		
		List<InventoryInfoDto> items = service.execute(noFilter());
		
		InventoryInfoDto info = items.get(0);
		
		assertEquals(1, items.size());
		
		assertEquals(20, info.getIncomings());
		
		assertEquals(4, info.getOutgoings());
		
		assertEquals(16, info.getAvailableItems());
	}
	
	private void mockProducts(String productId) {
		Category category = Category.of("f3559fb4-ea4a-4c86-b889-e0838a0719c5", "T-shirts");
		Franchise franchise = Franchise.of("9878cdc6-d089-405f-9f4d-5d53dcc79726", "Marvel");
		Product product = Product.of(productId, "001", "Product 1", new BigDecimal("100"), new BigDecimal("200"), category.getId(), franchise.getId());
		
		List<Product> products = Arrays.asList(product);
		
		Mockito.when(productRepository.findAll()).thenReturn(products);
	}
	
	private void mockMovements(String productId) {
		
		Movement incomingMovement = Movement.of(
										"c26907bb-adf4-4160-96e0-20545a3543ef", 
										productId, 
										MovementType.INCOMINGS, 
										20, 
										new BigDecimal("30000"), 
										"", 
										LocalDateTime.now()
									);
		
		Movement outgoingMovement = Movement.of(
										"658de796-4a07-4f4d-986f-4cd60b1004f9", 
										productId, 
										MovementType.OUTGOINGS, 
										4, 
										new BigDecimal("34000"), 
										"", 
										LocalDateTime.now()
									);
		
		List<Movement> allMovements = Arrays.asList( incomingMovement, outgoingMovement );
		
		Mockito.when(movementRepository.findAll()).thenReturn(allMovements);
		
	}
	
	private FilterOptions noFilter() {
		return new FilterOptions();
	}
	
}
