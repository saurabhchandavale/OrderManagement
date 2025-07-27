package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.CategoryRequest;
import com.example.dto.ProductRequest;
import com.example.dto.ProductResponse;
import com.example.entity.Category;
import com.example.entity.Product;
import com.example.exception.ResourceNotFoundException;
import com.example.reporitory.CategoryRepository;
import com.example.reporitory.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;

	private ProductResponse toResponse(Product product) {
		Category category = product.getCategory();
		return ProductResponse.builder().id(product.getId()).name(product.getName()).price(product.getPrice())
				.description(product.getDescription()).stock(product.getStock()).category(category).build();
	}

	@Override
	public ProductResponse createProduct(ProductRequest request) {

		Category category = Category.builder().name(request.getCategory().getName())
				.description(request.getCategory().getDescription()).build();
		Category save = categoryRepository.save(category);

		Product product = Product.builder().name(request.getName()).price(request.getPrice()).stock(request.getStock())
				.description(request.getDescription()).category(save).build();
		Product productSaved = productRepository.save(product);

		return toResponse(productSaved);
	}

	@Override
	public ProductResponse getProductById(Long id) {
		Product productById = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not fountd with id " + id));
		Optional<Category> category = categoryRepository.findById(productById.getCategory().getId());
		return toResponse(productById);
	}

	@Override
	public List<ProductResponse> getAllProducts() {
		List<Product> allProducts = productRepository.findAll();
		List<ProductResponse> productDTO = allProducts.stream().map(p -> toResponse(p)).collect(Collectors.toList());
		return productDTO;
	}

	@Override
	public ProductResponse updateProduct(Long id, ProductRequest request) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not fountd with id " + id));
		product.setName(request.getName());
		product.setPrice(request.getPrice());
		product.setDescription(request.getDescription());
		product.setStock(request.getStock());
		Product updatedProduct = productRepository.save(product);
		return toResponse(updatedProduct);
	}

	@Override
	public void deleteProduct(Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
		productRepository.delete(product);

	}

}
