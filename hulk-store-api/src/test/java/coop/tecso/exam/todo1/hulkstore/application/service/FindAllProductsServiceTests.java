package coop.tecso.exam.todo1.hulkstore.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
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

import coop.tecso.exam.todo1.hulkstore.application.dto.ProductDto;
import coop.tecso.exam.todo1.hulkstore.application.request.FilterOptions;
import coop.tecso.exam.todo1.hulkstore.domain.model.Category;
import coop.tecso.exam.todo1.hulkstore.domain.model.Franchise;
import coop.tecso.exam.todo1.hulkstore.domain.model.Product;
import coop.tecso.exam.todo1.hulkstore.domain.repository.CategoryRepository;
import coop.tecso.exam.todo1.hulkstore.domain.repository.FranchiseRepository;
import coop.tecso.exam.todo1.hulkstore.domain.repository.ProductRepository;
import coop.tecso.exam.todo1.hulkstore.domain.service.CategoryService;
import coop.tecso.exam.todo1.hulkstore.domain.service.FranchiseService;
import coop.tecso.exam.todo1.hulkstore.domain.service.ProductService;

/**
 * Use case: It gets a list of all the products stored in the database
 * @author Cristian
 *
 */

@ExtendWith(MockitoExtension.class)

final class FindAllProductsServiceTests {

	@Mock
	private FranchiseRepository franchiseRepository;
	
	@Mock
	private CategoryRepository categoryRepository;
	
	@Mock
	private ProductRepository productRepository;
	
	private ProductService productService;
	
	private CategoryService categoryService;
	
	private FranchiseService franchiseService;
	
	private FindAllProductsService service;
	
	@BeforeEach
	public void setUp() {
		productService = new ProductService(productRepository);
		categoryService = new CategoryService(categoryRepository);
		franchiseService = new FranchiseService(franchiseRepository);
		service = new FindAllProductsService(productService, categoryService, franchiseService);
	}
	
	@Test
	@DisplayName("It should get an empty list of products")
	void shouldGetAnEmptyListOfProducts() {
		
		Mockito.when(productRepository.findAll()).thenReturn(Collections.emptyList());
		
		List<ProductDto> allProducts = service.execute(noFilter());
		
		assertNotNull(allProducts);
		assertEquals(0, allProducts.size());
		
	}
	
	@Test
	@DisplayName("It should get an non-empty list of products")
	void shouldGetAnNonEmptyListOfProducts() {
		
		Mockito.when(categoryRepository.findAll()).thenReturn(categories());
		Mockito.when(franchiseRepository.findAll()).thenReturn(franchises());
		Mockito.when(productRepository.findAll()).thenReturn(products());
		
		List<ProductDto> allProducts = service.execute(noFilter());
		
		assertNotNull(allProducts);
		assertEquals(2, allProducts.size());
		
	}
	
	public List<Product> products() {
		
		Product p1 = Product.of("dc3029fc-ffd2-4d2b-9c2f-6c9c01ef4040", "001", "Product 1", new BigDecimal("100"), new BigDecimal("200"), "7e7937a6-e008-42f9-b619-d15a41108b8a", "f3d0a258-ab7a-4a2f-864a-f3acff3450e3");
		Product p2 = Product.of("f624d3ed-6899-4dbb-8871-6ac3dbab20bb", "001", "Product 1", new BigDecimal("100"), new BigDecimal("200"), "f3559fb4-ea4a-4c86-b889-e0838a0719c5", "9878cdc6-d089-405f-9f4d-5d53dcc79726");
		
		return Arrays.asList(p1, p2);
		
	}
	
	private List<Category> categories() {
		Category tShirts = Category.of("f3559fb4-ea4a-4c86-b889-e0838a0719c5", "T-shirts");
		Category toys    = Category.of("7e7937a6-e008-42f9-b619-d15a41108b8a", "Toys");
		return Arrays.asList( tShirts, toys );
	}
	
	private List<Franchise> franchises() {
		Franchise marvel = Franchise.of("9878cdc6-d089-405f-9f4d-5d53dcc79726", "Marvel");
		Franchise dc = Franchise.of("f3d0a258-ab7a-4a2f-864a-f3acff3450e3", "DC");
		Franchise others = Franchise.of("4e5d622d-895b-429e-b564-63ed7ebc7820", "Others");
		
		return Arrays.asList(marvel, dc, others);
	}
	
	private FilterOptions noFilter() {
		return new FilterOptions();
	}
	
}
