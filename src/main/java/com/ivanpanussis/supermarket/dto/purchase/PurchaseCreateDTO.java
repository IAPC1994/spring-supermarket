package com.ivanpanussis.supermarket.dto.purchase;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.ivanpanussis.supermarket.dto.purchasedetail.PurchaseDetailDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseCreateDTO {
	@DateTimeFormat
	private LocalDate date;
	
	@NotBlank(message = "Purchase status is required")
	private String status;
	
	
	private Long branchId;
	
	@NotEmpty(message = "Purchase detail is required")
	private List<PurchaseDetailDTO> detail;
}
