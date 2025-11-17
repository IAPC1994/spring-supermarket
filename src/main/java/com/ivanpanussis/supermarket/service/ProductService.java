package com.ivanpanussis.supermarket.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ivanpanussis.supermarket.dto.product.ProductCreateDTO;
import com.ivanpanussis.supermarket.dto.product.ProductResponseDTO;
import com.ivanpanussis.supermarket.dto.product.ProductUpdateDTO;
import com.ivanpanussis.supermarket.mapper.Mapper;
import com.ivanpanussis.supermarket.model.Product;
import com.ivanpanussis.supermarket.repository.ProductRepository;


@Service
public class ProductService implements IProductService{
	
	private final ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<ProductResponseDTO> findAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAll().stream().map(Mapper::toDTO).toList();
	}

	@Override
	public ProductResponseDTO findProductById(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).map(Mapper::toDTO)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
	}

	@Override
	public ProductResponseDTO createProduct(ProductCreateDTO productDto) {
		// TODO Auto-generated method stub
		var prod = Product.builder()
				.name(productDto.getName())
				.category(productDto.getCategory())
				.price(productDto.getPrice())
				.stock(productDto.getStock())
				.build();
		return Mapper.toDTO(productRepository.save(prod));
	}

	@Override
	public ProductResponseDTO updateProduct(Long id, ProductUpdateDTO productDto) {
		// TODO Auto-generated method stub
		Product product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
		if(productDto.getName() != null) {
			product.setName(productDto.getName());
		}
		
		if(productDto.getCategory() != null) {
			product.setCategory(productDto.getCategory());
		}
		
		if(productDto.getPrice() != null) {
			product.setPrice(productDto.getPrice());
		}
		
		if(productDto.getStock() != null) {
			product.setStock(productDto.getStock());
		}
		
		
		return Mapper.toDTO(productRepository.save(product));
	}

	@Override
	public void deleteProduct(Long id) {
		// TODO Auto-generated method stub
		if(!productRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
		}
		productRepository.deleteById(id);
	}

}
