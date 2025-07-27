package com.example.dto;



import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;
    
}
