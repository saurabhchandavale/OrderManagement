package com.example.service;

import com.example.dto.CategoryRequest;
import com.example.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
	CategoryResponse createCategory(CategoryRequest request);

	List<CategoryResponse> getAllCategories();

	CategoryResponse getCategoryById(Long id);

	void deleteCategory(Long id);
}
