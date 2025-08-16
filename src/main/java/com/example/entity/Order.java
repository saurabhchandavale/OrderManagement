package com.example.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long productId;
	private Long userId;
	private Integer quantity;
	private String status;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		updatedAt = LocalDateTime.now();
	}
}
