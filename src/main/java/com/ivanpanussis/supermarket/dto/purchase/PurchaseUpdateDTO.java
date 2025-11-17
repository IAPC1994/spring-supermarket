package com.ivanpanussis.supermarket.dto.purchase;

import java.time.LocalDate;
import java.util.List;

import com.ivanpanussis.supermarket.model.PurchaseDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseUpdateDTO {
	
	private LocalDate date;
	private String status;
	private Double total;
	
	private Long branchId;
	private List<PurchaseDetail> detail;
}
