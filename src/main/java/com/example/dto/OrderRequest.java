package com.example.dto;

import jakarta.validation.constraints.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderRequest {

    @NotNull(message = "productId is required")
    private Long productId;

    @NotNull(message = "userId is required")
    private Long userId;

    @NotNull(message = "quantity is required")
    @Min(value = 1, message = "Minimum quantity is 1")
    private Integer quantity;

    @NotBlank(message = "status must not be blank")
    private String status;
}

