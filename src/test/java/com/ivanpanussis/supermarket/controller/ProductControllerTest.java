package com.ivanpanussis.supermarket.controller;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivanpanussis.supermarket.dto.product.ProductCreateDTO;
import com.ivanpanussis.supermarket.dto.product.ProductResponseDTO;
import com.ivanpanussis.supermarket.dto.product.ProductUpdateDTO;
import com.ivanpanussis.supermarket.service.ProductService;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService service;

    @Test
    void testFindAll() throws Exception {
        ProductResponseDTO dto = new ProductResponseDTO(1L, "Milk", "Dairy", 5.5, 10);

        when(service.findAllProducts()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Milk"));

        verify(service, times(1)).findAllProducts();
    }

    @Test
    void testFindById() throws Exception {
        ProductResponseDTO dto = new ProductResponseDTO(1L, "Bread", "Bakery", 10.0, 5);

        when(service.findProductById(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bread"));

        verify(service).findProductById(1L);
    }

    @Test
    void testCreateProduct() throws Exception {

        ProductCreateDTO createDTO = new ProductCreateDTO();
        createDTO.setName("Cheese");
        createDTO.setCategory("Dairy");
        createDTO.setPrice(15.0);
        createDTO.setStock(20);

        ProductResponseDTO responseDTO = new ProductResponseDTO(1L, "Cheese", "Dairy", 15.0, 20);

        when(service.createProduct(any(ProductCreateDTO.class)))
        .thenReturn(responseDTO);

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/products/1"))
                .andExpect(jsonPath("$.name").value("Cheese"));

        verify(service).createProduct(any(ProductCreateDTO.class));
    }

    @Test
    void testUpdateProduct() throws Exception {
        ProductUpdateDTO updateDTO = new ProductUpdateDTO();
        updateDTO.setName("Meat");

        ProductResponseDTO updatedDTO = new ProductResponseDTO(1L, "Meat", "Food", 10.0, 2);

        when(service.updateProduct(eq(1L), any(ProductUpdateDTO.class))).thenReturn(updatedDTO);

        mockMvc.perform(patch("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Meat"));

        verify(service).updateProduct(eq(1L), any(ProductUpdateDTO.class));
    }

    @Test
    void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isNoContent());

        verify(service).deleteProduct(1L);
    }
}
