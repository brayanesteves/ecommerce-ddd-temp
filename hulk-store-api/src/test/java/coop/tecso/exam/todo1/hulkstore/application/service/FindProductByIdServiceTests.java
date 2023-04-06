package coop.tecso.exam.todo1.hulkstore.application.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import coop.tecso.exam.todo1.hulkstore.application.dto.ProductDto;
import coop.tecso.exam.todo1.hulkstore.domain.model.Category;
import coop.tecso.exam.todo1.hulkstore.domain.model.Franchise;
import coop.tecso.exam.todo1.hulkstore.domain.model.Product;
import coop.tecso.exam.todo1.hulkstore.domain.repository.CategoryRepository;
import coop.tecso.exam.todo1.hulkstore.domain.repository.FranchiseRepository;
import coop.tecso.exam.todo1.hulkstore.domain.repository.ProductRepository;
import coop.tecso.exam.todo1.hulkstore.domain.service.CategoryService;
import coop.tecso.exam.todo1.hulkstore.domain.service.FranchiseService;
import coop.tecso.exam.todo1.hulkstore.domain.service.ProductService;
import coop.tecso.exam.todo1.hulkstore.domain.service.exceptions.ProductDoesNotExistException;

@ExtendWith(MockitoExtension.class)

final class FindProductByIdServiceTests {
	
	@Mock
	private ProductRepository productRepository;
	
	@Mock
	private CategoryRepository categoryRepository;
	
	@Mock
	private FranchiseRepository franchiseRepository;
	
	private ProductService productService;
	
	private CategoryService categoryService;
	
	private FranchiseService franchiseService;
	
	private FindProductByIdService service;
	
	
	@BeforeEach
	public void setUp() {
		productService = new ProductService(productRepository);
		categoryService = new CategoryService(categoryRepository);
		franchiseService = new FranchiseService(franchiseRepository);
		service = new FindProductByIdService(productService, categoryService, franchiseService);
	}

	@Test
	@DisplayName("should throw an exception because product id does not exist")
	void shouldNotFindAnyProduct() {
		
		assertThrows(ProductDoesNotExistException.class, () -> service.execute("unknown-product-id"));
		
	}
	
	@Test
	@DisplayName("Should find an existing product")
	void shouldFindAnExistingProduct() {
		
		Category category = Category.of("f3559fb4-ea4a-4c86-b889-e0838a0719c5", "T-shirts");
		
		Franchise franchise = Franchise.of("9878cdc6-d089-405f-9f4d-5d53dcc79726", "Marvel");
		
		Product product = Product.of("dc3029fc-ffd2-4d2b-9c2f-6c9c01ef4040", "001", "Product 1", new BigDecimal("100"), new BigDecimal("200"), category.getId(), franchise.getId());
		
		Mockito.when( productRepository.findById(product.getId()) ).thenReturn( Optional.of(product) );
		
		Mockito.when(categoryRepository.findById(category.getId())).thenReturn( Optional.of(category) );
		
		Mockito.when(franchiseRepository.findById(franchise.getId())).thenReturn( Optional.of(franchise) );
		
		ProductDto dto = service.execute(product.getId());
		
		assertNotNull(dto);
		
	}
	
}
