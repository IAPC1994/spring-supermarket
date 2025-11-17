package com.ivanpanussis.supermarket.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {

	private Long id;
	private String name;
	private String category;
	private Double price;
	private int stock;
}
