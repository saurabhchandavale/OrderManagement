package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ProductRequest;
import com.example.dto.ProductResponse;
import com.example.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	ProductService productService;

	@PostMapping("/create")
	public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest request) {
		ProductResponse product = productService.createProduct(request);
		return new ResponseEntity<ProductResponse>(product, HttpStatus.CREATED);

	}

	@GetMapping("/")
	public ResponseEntity<List<ProductResponse>> getAllProducts() {
		List<ProductResponse> allProducts = productService.getAllProducts();
		return new ResponseEntity<List<ProductResponse>>(allProducts, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
		ProductResponse productById = productService.getProductById(id);
		return new ResponseEntity<ProductResponse>(productById, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductResponse> updateProductById(@PathVariable Long id,
			@RequestBody ProductRequest request) {
		ProductResponse product = productService.updateProduct(id, request);
		return new ResponseEntity<ProductResponse>(product, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
		productService.deleteProduct(id);
		return new ResponseEntity<String>("Product Deled Successfully", HttpStatus.OK);
	}

}
