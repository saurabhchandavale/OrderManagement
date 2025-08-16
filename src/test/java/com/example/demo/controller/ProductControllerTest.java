package com.example.demo.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.example.controller.ProductController;
import com.example.dto.ProductResponse;
import com.example.entity.Product;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private com.example.service.ProductService productService;

    // Helper method to create a sample Product with nested Category
    private ProductResponse createSampleProduct() {
        com.example.entity.Category category = new com.example.entity.Category();
        category.setId(1L);
        category.setName("Headphones");
        category.setDescription("Audio equipment");

        ProductResponse product = new ProductResponse();
        product.setId(1L);
        product.setName("Boat WZ-1000XM5");
        product.setPrice(499.99);
        product.setDescription("Noise cancelling wireless headphones");
        product.setStock(25);
        product.setCategory(category);

        return product;
    }

    // ------------------- GET /api/products -------------------
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testGetProductsAsUser() throws Exception {
        // Mock the service to return a list with one sample product
        when(productService.getAllProducts())
                .thenReturn(Arrays.asList(createSampleProduct()));

        // Perform GET request and check response
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Boat WZ-1000XM5"))
                .andExpect(jsonPath("$[0].category.name").value("Headphones"));
    }
}
