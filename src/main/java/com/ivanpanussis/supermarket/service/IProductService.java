package com.ivanpanussis.supermarket.service;

import java.util.List;

import com.ivanpanussis.supermarket.dto.product.ProductCreateDTO;
import com.ivanpanussis.supermarket.dto.product.ProductResponseDTO;
import com.ivanpanussis.supermarket.dto.product.ProductUpdateDTO;

public interface IProductService {

	List<ProductResponseDTO> findAllProducts();
	ProductResponseDTO findProductById(Long id);
	ProductResponseDTO createProduct(ProductCreateDTO productDto);
	ProductResponseDTO updateProduct(Long id, ProductUpdateDTO productDto);
	void deleteProduct(Long id);
}
