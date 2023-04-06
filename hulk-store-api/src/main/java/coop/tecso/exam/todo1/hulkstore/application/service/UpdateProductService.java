package coop.tecso.exam.todo1.hulkstore.application.service;

import org.springframework.stereotype.Service;

import coop.tecso.exam.todo1.hulkstore.application.request.UpdateProductRequest;
import coop.tecso.exam.todo1.hulkstore.domain.model.Product;
import coop.tecso.exam.todo1.hulkstore.domain.model.ProductBuilder;
import coop.tecso.exam.todo1.hulkstore.domain.service.CategoryService;
import coop.tecso.exam.todo1.hulkstore.domain.service.FranchiseService;
import coop.tecso.exam.todo1.hulkstore.domain.service.ProductService;
import coop.tecso.exam.todo1.hulkstore.domain.validator.FieldValidator;

@Service

public class UpdateProductService {
	
	private ProductService productService;
	private CategoryService categoryService;
	private FranchiseService franchiseService;
	
	public UpdateProductService(ProductService productService, CategoryService categoryService,
			FranchiseService franchiseService) {
		this.productService = productService;
		this.categoryService = categoryService;
		this.franchiseService = franchiseService;
	}

	public void execute(UpdateProductRequest request) {
		
		FieldValidator.notNull(request, "UpdateProductRequest");
		
		Product product = ProductBuilder.newInstance()
				                        .id(request.getId())
				                        .code(request.getCode())
				                        .name(request.getName())
				                        .purchasePrice(request.getPurchasePrice())
				                        .sellingPrice(request.getSellingPrice())
				                        .categoryId(request.getCategoryId())
				                        .franchiseId(request.getFranchiseId())
				                        .build();
		
		productService.checkIfProductExists(request.getId());
		
        productService.checkIfCodeIsAvailable(product.getId(), product.getCode());
		
		categoryService.checkIfCategoryExists(product.getCategoryId());
		
		franchiseService.checkIfFranchiseExists(product.getFranchiseId());
		
		productService.save(product);
		
	}

}
