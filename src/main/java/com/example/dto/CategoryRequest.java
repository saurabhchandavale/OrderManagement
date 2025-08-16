package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CategoryRequest {
	@NotBlank(message = "Category name is required")
	private String name;
	private String description;
}
