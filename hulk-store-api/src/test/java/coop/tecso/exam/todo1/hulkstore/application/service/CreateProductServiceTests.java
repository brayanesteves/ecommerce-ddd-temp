package coop.tecso.exam.todo1.hulkstore.application.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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

import coop.tecso.exam.todo1.hulkstore.application.request.CreateProductRequest;
import coop.tecso.exam.todo1.hulkstore.domain.model.Category;
import coop.tecso.exam.todo1.hulkstore.domain.model.Franchise;
import coop.tecso.exam.todo1.hulkstore.domain.model.Product;
import coop.tecso.exam.todo1.hulkstore.domain.model.ProductBuilder;
import coop.tecso.exam.todo1.hulkstore.domain.repository.CategoryRepository;
import coop.tecso.exam.todo1.hulkstore.domain.repository.FranchiseRepository;
import coop.tecso.exam.todo1.hulkstore.domain.repository.ProductRepository;
import coop.tecso.exam.todo1.hulkstore.domain.service.CategoryService;
import coop.tecso.exam.todo1.hulkstore.domain.service.FranchiseService;
import coop.tecso.exam.todo1.hulkstore.domain.service.ProductService;
import coop.tecso.exam.todo1.hulkstore.domain.service.exceptions.CategoryDoesNotExistException;
import coop.tecso.exam.todo1.hulkstore.domain.service.exceptions.FranchiseDoesNotExistException;
import coop.tecso.exam.todo1.hulkstore.domain.service.exceptions.ProductCodeAlreadyExistsException;
import coop.tecso.exam.todo1.hulkstore.domain.validator.InvalidFieldException;

@ExtendWith(MockitoExtension.class)

final class CreateProductServiceTests {

	@Mock
	private FranchiseRepository franchiseRepository;
	
	@Mock
	private CategoryRepository categoryRepository;
	
	@Mock
	private ProductRepository productRepository;
	
	private ProductService productService;
	
	private CategoryService categoryService;
	
	private FranchiseService franchiseService;
	
	private CreateProductService service;
	
	@BeforeEach
	public void setUp() {
		productService = new ProductService(productRepository);
		categoryService = new CategoryService(categoryRepository);
		franchiseService = new FranchiseService(franchiseRepository);
		service = new CreateProductService(productService, categoryService, franchiseService);
	}
	
	@Test
	@DisplayName("Cannot invoke service with a null parameter")
	void cannotInvokeServiceWithNullParameter() {
		
		assertThrows(InvalidFieldException.class, () -> service.execute(null) );
		
	}
	
	@Test
	@DisplayName("Cannot create a product with null or empty fields.")
	void cannotCreateProductWithEmptyFields() {
		
		CreateProductRequest request = requestWithEmptyFields();
		
		assertThrows(InvalidFieldException.class, () -> service.execute(request) );
		
	}
	
	@Test
	@DisplayName("Create a valid product")
	void createAValidProduct() {

		CreateProductRequest request = someRequest();
		
		Mockito.when(categoryRepository.findById(request.getCategoryId())).thenReturn(Optional.of(Category.of(request.getCategoryId(), "Comics")));
		
		Mockito.when(franchiseRepository.findById(request.getFranchiseId())).thenReturn(Optional.of(Franchise.of(request.getFranchiseId(), "Marvel")));

		assertDoesNotThrow(() -> service.execute(request) ); 
		
	}
	
	@Test
	@DisplayName("Should not create a product if the category does not exist")
	void shouldNotCreateIfCategoryIsInvalid() {
		
		CreateProductRequest request = someRequest();
		
		String categoryId = request.getCategoryId();
		
		Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());
		
		assertThrows(CategoryDoesNotExistException.class, () -> service.execute(request) );
		
	}
	
	@Test
	@DisplayName("Should not create a product if the franchise does not exist")
	void shouldNotCreateIfFranchiseIsInvalid() {
		
		CreateProductRequest request = someRequest();
		
		String franchiseId = request.getFranchiseId();
		
		Mockito.when(categoryRepository.findById(request.getCategoryId())).thenReturn(Optional.of(Category.of(request.getCategoryId(), "Comics")));
		Mockito.when(franchiseRepository.findById(franchiseId)).thenReturn(Optional.empty());
		
		assertThrows(FranchiseDoesNotExistException.class, () -> service.execute(request) );
		
	}
	
	@Test
	@DisplayName("Should not create a product if the CODE is already registered")
	void productCodeMustBeUnique() {
		
		CreateProductRequest request = someRequest();
		
		Mockito.when(categoryRepository.findById(request.getCategoryId())).thenReturn(Optional.of(Category.of(request.getCategoryId(), "Comics")));
		
		Mockito.when(franchiseRepository.findById(request.getFranchiseId())).thenReturn(Optional.of(Franchise.of(request.getFranchiseId(), "Marvel")));
		
		assertDoesNotThrow(() -> service.execute(request) ); 
		
		Product product = ProductBuilder.newInstance().id(request.getId())
				                                      .name(request.getName())
				                                      .code(request.getCode())
				                                      .categoryId(request.getCategoryId())
				                                      .franchiseId(request.getFranchiseId())
				                                      .purchasePrice(new BigDecimal("1"))
				                                      .sellingPrice(new BigDecimal("1"))
				                                      .build();
		
		Mockito.when(productRepository.findByCode(request.getCode())).thenReturn( Optional.of(product) );
		
		assertThrows(ProductCodeAlreadyExistsException.class, () -> service.execute(request) );
		
	}
	
	private CreateProductRequest someRequest() {
		CreateProductRequest request = new CreateProductRequest();
		request.setId("443b5be9-9e8c-46b7-af0e-1810da29a0f4");
		request.setCode("C001XYZ");
		request.setName("Spiderman comic #001");
		request.setPurchasePrice(new BigDecimal("35898800"));
		request.setSellingPrice(new BigDecimal("40000000"));
		request.setCategoryId("3e15302c-c62f-4572-af48-db4aaae89624");
		request.setFranchiseId("32a97d92-4db2-48f9-a46b-aab7485cd47e");
		return request;
	}
	
	private CreateProductRequest requestWithEmptyFields() {
		CreateProductRequest request = new CreateProductRequest();
		request.setId("");
		return request;
	}
	
}
