package com.example.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderResponse {

	private Long id;
	private Long productId;
	private Long userId;
	private Integer quantity;
	private String status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
