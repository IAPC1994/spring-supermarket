package com.ivanpanussis.supermarket.dto.purchase;


import java.time.LocalDate;
import java.util.List;

import com.ivanpanussis.supermarket.dto.purchasedetail.PurchaseDetailDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseResponseDTO {

	private Long id;
	private LocalDate date;
	private String status;
	private Double total;
	
	private Long branchId;
	private List<PurchaseDetailDTO> detail;
	
}
