package com.ivanpanussis.supermarket.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import com.ivanpanussis.supermarket.dto.product.ProductCreateDTO;
import com.ivanpanussis.supermarket.dto.product.ProductResponseDTO;
import com.ivanpanussis.supermarket.dto.product.ProductUpdateDTO;
import com.ivanpanussis.supermarket.model.Product;
import com.ivanpanussis.supermarket.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@Mock
	private ProductRepository repo;
	
	@InjectMocks
	private ProductService service;
	
	@Test
	void findAllProducts_ReturnsDTO() {
		Product p1 = new Product(1L, "Oranges", "Fruits", 1.5, 50);
		Product p2 = new Product(1L, "Apples", "Fruits", 1.5, 100);
		when(repo.findAll()).thenReturn(List.of(p1, p2));
		
		List<ProductResponseDTO> result = service.findAllProducts();
		
		assertEquals(2, result.size());
		assertEquals("Oranges", result.get(0).getName());
		assertEquals("Apples", result.get(1).getName());
		
		verify(repo, times(1)).findAll();
	}
	
	// Good case
	@Test
	void testFindProductById_ReturnsDTO() {
		Product p1 = Product.builder()
				.id(1L)
				.name("Bread")
				.category("Food")
				.price(10.0)
				.stock(5)
				.build();
		
		when(repo.findById(1L)).thenReturn(Optional.of(p1));
		
		ProductResponseDTO result = service.findProductById(1L);
		
		assertNotNull(result);
		assertEquals("Bread", result.getName());
		verify(repo, times(1)).findById(1L);
	}
	
	// Bad case
	@Test
	void testFindProductById_NotFound_ThrowsException() {
	    when(repo.findById(1L)).thenReturn(Optional.empty());

	    assertThrows(ResponseStatusException.class, () -> {
	        service.findProductById(1L);
	    });

	    verify(repo, times(1)).findById(1L);
	}
	
	@Test
	void testCreateProduct() {
	  
	    ProductCreateDTO dto = new ProductCreateDTO();
	    dto.setName("Milk");
	    dto.setCategory("Dairy");
	    dto.setPrice(5.5);
	    dto.setStock(10);

	    Product saved = Product.builder()
	            .id(1L)
	            .name("Milk")
	            .category("Dairy")
	            .price(5.5)
	            .stock(10)
	            .build();

	    when(repo.save(any(Product.class))).thenReturn(saved);
	    
	    ProductResponseDTO result = service.createProduct(dto);

	    assertNotNull(result);
	    assertEquals("Milk", result.getName());
	    verify(repo, times(1)).save(any(Product.class));
	}
	
	@Test
	void testUpdateProduct() {
	   
	    Product existing = Product.builder()
	            .id(1L)
	            .name("Old")
	            .category("OldCat")
	            .price(1.0)
	            .stock(1)
	            .build();

	    ProductUpdateDTO dto = new ProductUpdateDTO();
	    dto.setName("New");
	    dto.setCategory("NewCat");
	    dto.setPrice(20.0);
	    dto.setStock(50);

	    when(repo.findById(1L)).thenReturn(Optional.of(existing));
	    when(repo.save(any(Product.class))).thenReturn(existing);

	   
	    ProductResponseDTO result = service.updateProduct(1L, dto);

	   
	    assertEquals("New", existing.getName());
	    assertEquals("NewCat", existing.getCategory());
	    assertEquals(20.0, existing.getPrice());
	    assertEquals(50, existing.getStock());

	    verify(repo).findById(1L);
	    verify(repo).save(existing);
	}
	
	@Test
	void testUpdateProduct_NotFound() {
	    when(repo.findById(1L)).thenReturn(Optional.empty());

	    assertThrows(ResponseStatusException.class, () -> {
	        service.updateProduct(1L, new ProductUpdateDTO());
	    });

	    verify(repo).findById(1L);
	}

	@Test
	void testDeleteProduct() {
	    when(repo.existsById(1L)).thenReturn(true);

	    service.deleteProduct(1L);

	    verify(repo).existsById(1L);
	    verify(repo).deleteById(1L);
	}
	
	@Test
	void testDeleteProduct_NotFound() {
	    when(repo.existsById(1L)).thenReturn(false);

	    assertThrows(ResponseStatusException.class, () -> {
	        service.deleteProduct(1L);
	    });

	    verify(repo).existsById(1L);
	    verify(repo, never()).deleteById(any());
	}
}
