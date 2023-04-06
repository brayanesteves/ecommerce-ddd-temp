package coop.tecso.exam.todo1.hulkstore.application.service;

import org.springframework.stereotype.Service;

import coop.tecso.exam.todo1.hulkstore.application.dto.ProductDto;
import coop.tecso.exam.todo1.hulkstore.domain.model.Category;
import coop.tecso.exam.todo1.hulkstore.domain.model.Franchise;
import coop.tecso.exam.todo1.hulkstore.domain.model.Product;
import coop.tecso.exam.todo1.hulkstore.domain.service.CategoryService;
import coop.tecso.exam.todo1.hulkstore.domain.service.FranchiseService;
import coop.tecso.exam.todo1.hulkstore.domain.service.ProductService;
import coop.tecso.exam.todo1.hulkstore.domain.validator.FieldValidator;

@Service

public class FindProductByIdService {

	private ProductService productService;
	
	private CategoryService categoryService;
	
	private FranchiseService franchiseService;

	public FindProductByIdService(ProductService productService, CategoryService categoryService,
			FranchiseService franchiseService) {
		this.productService = productService;
		this.categoryService = categoryService;
		this.franchiseService = franchiseService;
	}
	
	public ProductDto execute(String productId) {
		
		FieldValidator.notNull(productId, "productId");
		
		Product product = productService.findById(productId);
		
		Category category = categoryService.findById(product.getCategoryId());
		
		Franchise franchise = franchiseService.findById(product.getFranchiseId());
		
		return ProductDto.from(product, category, franchise);
		
	}
	
}
