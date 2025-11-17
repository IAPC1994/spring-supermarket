package com.ivanpanussis.supermarket.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateDTO {
	@NotBlank(message = "Product name is required")
	private String name;
	@NotBlank(message="Product category is required")
	private String category;
	
	@Min(value = 0, message = "Price price cannot be less than 0")
	private Double price;
	
	@Min(value = 0, message = "Product stock cannot be less than 0")
	private int stock;
}
