package coop.tecso.exam.todo1.hulkstore.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

import coop.tecso.exam.todo1.hulkstore.application.dto.FranchiseDto;
import coop.tecso.exam.todo1.hulkstore.domain.model.Franchise;
import coop.tecso.exam.todo1.hulkstore.domain.repository.FranchiseRepository;
import coop.tecso.exam.todo1.hulkstore.domain.service.FranchiseService;

/**
 * Use case: Get all the franchises. 
 * For example: Marvel, DC, etc.
 */

@ExtendWith(MockitoExtension.class)

final class FindAllFranchisesServiceTests {

	@Mock
	private FranchiseRepository franchiseRepository;
	
	private FranchiseService franchiseService;
	
	private FindAllFranchisesService service;
	
	@BeforeEach
	public void setUp() {
		franchiseService = new FranchiseService(franchiseRepository);
		service = new FindAllFranchisesService(franchiseService);
	}
	
	@Test
	@DisplayName("Should get a non empty list of franchises")
	void shouldGetANonEmptyList() {
		
		int expectedFranchises = 3;
		
		mockFranchisesInDBWith(getSomeFranchises());
		
		List<FranchiseDto> franchises = service.execute();
		
		assertNotNull(franchises);
		assertEquals(expectedFranchises, franchises.size());
		
	}
	
	
	@Test
	@DisplayName("Should get an empty list of franchises")
	void shouldGetAnEmptyList() {
		
		int expectedFranchises = 0;
		
		mockFranchisesInDBWith(Collections.emptyList());
		
		List<FranchiseDto> franchises = service.execute();
		
		assertNotNull(franchises);
		assertEquals(expectedFranchises, franchises.size());
		
	}
	
	private void mockFranchisesInDBWith(List<Franchise> mockList) {
		Mockito.when(franchiseRepository.findAll()).thenReturn(mockList);
	}
	
	private List<Franchise> getSomeFranchises() {
		Franchise marvel = Franchise.of("9878cdc6-d089-405f-9f4d-5d53dcc79726", "Marvel");
		Franchise dc = Franchise.of("f3d0a258-ab7a-4a2f-864a-f3acff3450e3", "DC");
		Franchise others = Franchise.of("4e5d622d-895b-429e-b564-63ed7ebc7820", "Others");
		
		return Arrays.asList(marvel, dc, others);
	}
	
}
