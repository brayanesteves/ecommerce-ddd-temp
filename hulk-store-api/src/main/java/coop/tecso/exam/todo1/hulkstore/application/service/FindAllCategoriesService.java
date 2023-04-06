package coop.tecso.exam.todo1.hulkstore.application.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import coop.tecso.exam.todo1.hulkstore.application.dto.CategoryDto;
import coop.tecso.exam.todo1.hulkstore.domain.service.CategoryService;

/**
 * It gets all the categories, sorted by name
 * @author Cristian
 *
 */

@Service

public class FindAllCategoriesService {

	private CategoryService categoryService;

	public FindAllCategoriesService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public List<CategoryDto> execute() {
		
		return categoryService.findAllCategories()
							  .stream()
							  .map(CategoryDto::fromModel)
							  .sorted(Comparator.comparing(CategoryDto::getName))
							  .collect(Collectors.toList());
		
	}
	
}
