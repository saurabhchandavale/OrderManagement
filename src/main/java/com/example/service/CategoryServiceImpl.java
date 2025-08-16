package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.CategoryRequest;
import com.example.dto.CategoryResponse;
import com.example.entity.Category;
import com.example.exception.ResourceNotFoundException;
import com.example.reporitory.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepository categoryRepository;

	private CategoryResponse toResponse(Category saved) {
		CategoryResponse category = CategoryResponse.builder().id(saved.getId()).name(saved.getName())
				.description(saved.getDescription()).build();

		return category;
	}

	@Override
	public CategoryResponse createCategory(CategoryRequest request) {
		Category category = Category.builder().name(request.getName()).description(request.getDescription()).build();
		Category saved = categoryRepository.save(category);
		return toResponse(saved);
	}

	@Override
	public List<CategoryResponse> getAllCategories() {
		List<Category> allCategory = categoryRepository.findAll();
		List<CategoryResponse> category = allCategory.stream().map(c -> toResponse(c)).collect(Collectors.toList());
		return category;
	}

	@Override
	public CategoryResponse getCategoryById(Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category Not found with Id " + id));
		return toResponse(category);
	}

	@Override
	public void deleteCategory(Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category Not found with Id " + id));
		categoryRepository.delete(category);
	}

}
