package com.ivanpanussis.supermarket.dto.purchasedetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseDetailDTO {

	private Long id;
	private String prodName;
	private Integer prodQuantity;
	private Double price;
	private Double subtotal;
}
