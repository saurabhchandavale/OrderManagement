package com.example.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductResponse {
	private Long id;
	private String name;
	private Double price;
	private String description;
	private Integer stock;
}
