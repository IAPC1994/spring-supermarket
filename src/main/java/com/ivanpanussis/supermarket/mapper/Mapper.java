package com.ivanpanussis.supermarket.mapper;

import java.util.stream.Collectors;

import com.ivanpanussis.supermarket.dto.branch.BranchResponseDTO;
import com.ivanpanussis.supermarket.dto.product.ProductResponseDTO;
import com.ivanpanussis.supermarket.dto.purchase.PurchaseResponseDTO;
import com.ivanpanussis.supermarket.dto.purchasedetail.PurchaseDetailDTO;
import com.ivanpanussis.supermarket.model.Branch;
import com.ivanpanussis.supermarket.model.Product;
import com.ivanpanussis.supermarket.model.Purchase;

public class Mapper {

	public static ProductResponseDTO toDTO(Product p) {
		if( p == null ) return null;
		
		return ProductResponseDTO.builder()
				.id(p.getId())
				.name(p.getName())
				.category(p.getCategory())
				.price(p.getPrice())
				.stock(p.getStock())
				.build();
	}
	
	public static PurchaseResponseDTO toDTO(Purchase p) {
		if(p == null) return null;
		
		var detail = p.getDetail().stream().map(t -> 
			PurchaseDetailDTO.builder()
				.id(t.getProduct().getId())
				.prodName(t.getProduct().getName())
				.prodQuantity(t.getProdQuantity())
				.price(t.getPrice()).subtotal(t.getPrice() * t.getProdQuantity())
				.build()
			).collect(Collectors.toList());
		
		var total = detail.stream()
				.map(PurchaseDetailDTO::getSubtotal)
				.reduce(0.0, Double::sum);
		
		return PurchaseResponseDTO.builder()
				.id(p.getId())
				.date(p.getDate())
				.branchId(p.getBranch().getId())
				.status(p.getStatus())
				.detail(detail)
				.total(total)
				.build();
	}
	
	public static BranchResponseDTO toDTO(Branch b) {
		if(b == null) return null;
		return BranchResponseDTO.builder()
				.id(b.getId())
				.name(b.getName())
				.address(b.getAddress())
				.build();
	}
}
