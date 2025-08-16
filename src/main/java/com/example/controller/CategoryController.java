package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CategoryRequest;
import com.example.dto.CategoryResponse;
import com.example.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	@Autowired
	CategoryService categoryService;

	@PostMapping("/create")
	public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest request) {
		CategoryResponse category = categoryService.createCategory(request);
		return new ResponseEntity<CategoryResponse>(category, HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<List<CategoryResponse>> getAllCategories() {
		List<CategoryResponse> allCategories = categoryService.getAllCategories();
		return new ResponseEntity<List<CategoryResponse>>(allCategories, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
		CategoryResponse category = categoryService.getCategoryById(id);
		return new ResponseEntity<CategoryResponse>(category, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategoryById(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return new ResponseEntity<String>("Category deleted successfully", HttpStatus.OK);

	}
}

