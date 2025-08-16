package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Product {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private Double price;

	private String description;

	private Integer stock;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

}
