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

import coop.tecso.exam.todo1.hulkstore.application.dto.MovementDto;
import coop.tecso.exam.todo1.hulkstore.domain.model.Movement;
import coop.tecso.exam.todo1.hulkstore.domain.model.MovementType;
import coop.tecso.exam.todo1.hulkstore.domain.repository.MovementRepository;
import coop.tecso.exam.todo1.hulkstore.domain.service.MovementService;

@ExtendWith(MockitoExtension.class)

final class FindAllMovementsServiceTests {

	@Mock
	private MovementRepository movementRepository;
	
	private MovementService movementService;
	
	private FindAllMovementsService service;
	
	@BeforeEach
	public void setUp() {
		movementService = new MovementService(movementRepository);
		service = new FindAllMovementsService(movementService);
	}
	
	@Test
	@DisplayName("Should get a non empty list of movements")
	void shouldGetANonEmptyList() {
		
		Mockito.when(movementRepository.findAll()).thenReturn(movements());
		
		int expectedMovements = 1;
		
		String productId = null;
		
		List<MovementDto> allMovements = service.execute(productId);
		
		assertEquals(expectedMovements, allMovements.size());
		
	}
	
	@Test
	@DisplayName("Should get an empty list of movements")
	void shouldGetAnEmptyList() {
		
		Mockito.when(movementRepository.findAll()).thenReturn(Collections.emptyList());
		
		int expectedMovements = 0;
		
		String productId = null;
		
		List<MovementDto> allMovements = service.execute(productId);
		
		assertEquals(expectedMovements, allMovements.size());
		
	}
	
	@Test
	@DisplayName("Should get all the movements by product id")
	void shouldGetAllTheMovementsByProduct() {
		
		String productId = "a5300e96-2968-467c-9f54-79eb0bedc94d";
		
		Mockito.when(movementRepository.findByProductId(productId)).thenReturn(movements());
		
		int expectedMovements = 1;
		
		List<MovementDto> allMovements = service.execute(productId);
		
		assertEquals(expectedMovements, allMovements.size());
		
	}
	
	private List<Movement> movements() {
		String id = "c26907bb-adf4-4160-96e0-20545a3543ef";
		String productId = "a5300e96-2968-467c-9f54-79eb0bedc94d";
		MovementType type = MovementType.INCOMINGS;
		Integer quantity = 10;
		BigDecimal price = new BigDecimal("3000");
		String observation = "Some observation";
		LocalDateTime createdAt = LocalDateTime.now();
		Movement movementOne = Movement.of(id, productId, type, quantity, price, observation, createdAt);
		
		return Arrays.asList(movementOne);
	}
	
}
