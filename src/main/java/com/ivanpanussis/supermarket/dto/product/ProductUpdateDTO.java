package com.ivanpanussis.supermarket.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateDTO {
	private String name;
	private String category;
	private Double price;
	private Integer stock;
}
