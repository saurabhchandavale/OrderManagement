package com.example.service;

import com.example.dto.ProductRequest;
import com.example.dto.ProductResponse;

import java.util.List;

public interface ProductService {
	ProductResponse createProduct(ProductRequest request);

	ProductResponse getProductById(Long id);

	List<ProductResponse> getAllProducts();

	ProductResponse updateProduct(Long id, ProductRequest request);

	void deleteProduct(Long id);
}

