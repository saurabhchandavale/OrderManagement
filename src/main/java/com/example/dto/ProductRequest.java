package com.example.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductRequest {

	@NotBlank(message = "Name is required")
	private String name;

	@NotNull(message = "Price is required")
	@DecimalMin(value = "0.0", inclusive = false, message = "Price must be positive")
	private Double price;

	private String description;

	@NotNull(message = "Stock is required")
	@Min(value = 0, message = "Stock must be non-negative")
	private Integer stock;

	@Valid
	@NotNull(message = "Category is required")
	private CategoryRequest category;
}
