package com.ivanpanussis.supermarket.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivanpanussis.supermarket.dto.product.ProductCreateDTO;
import com.ivanpanussis.supermarket.dto.product.ProductResponseDTO;
import com.ivanpanussis.supermarket.dto.product.ProductUpdateDTO;
import com.ivanpanussis.supermarket.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private ProductService productService;
	
	public ProductController(ProductService productService){
		this.productService = productService;
	}
	
	@GetMapping
	public ResponseEntity<List<ProductResponseDTO>> findAll(){
		return ResponseEntity.ok(productService.findAllProducts());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id){
		return ResponseEntity.ok(productService.findProductById(id));
	}
	
	@PostMapping
	public ResponseEntity<ProductResponseDTO> create(@Valid @RequestBody ProductCreateDTO dto){
		ProductResponseDTO createdProduct = productService.createProduct(dto);
		return ResponseEntity.created(URI.create("/api/products" + createdProduct.getId())).body(createdProduct);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ProductUpdateDTO dto){
		return ResponseEntity.ok(productService.updateProduct(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
}
