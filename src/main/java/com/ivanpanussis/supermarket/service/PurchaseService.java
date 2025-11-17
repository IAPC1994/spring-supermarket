package com.ivanpanussis.supermarket.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ivanpanussis.supermarket.dto.purchase.PurchaseCreateDTO;
import com.ivanpanussis.supermarket.dto.purchase.PurchaseResponseDTO;
import com.ivanpanussis.supermarket.dto.purchase.PurchaseUpdateDTO;
import com.ivanpanussis.supermarket.dto.purchasedetail.PurchaseDetailDTO;
import com.ivanpanussis.supermarket.mapper.Mapper;
import com.ivanpanussis.supermarket.model.Branch;
import com.ivanpanussis.supermarket.model.Product;
import com.ivanpanussis.supermarket.model.Purchase;
import com.ivanpanussis.supermarket.model.PurchaseDetail;
import com.ivanpanussis.supermarket.repository.BranchRepository;
import com.ivanpanussis.supermarket.repository.ProductRepository;
import com.ivanpanussis.supermarket.repository.PurchaseRepository;

@Service
public class PurchaseService implements IPurchaseService{

	private final PurchaseRepository purchaseRepository;
	private final ProductRepository productRepository;
	private final BranchRepository branchRepository;
	
	public PurchaseService(PurchaseRepository purchaseRepository, ProductRepository productRepository, BranchRepository branchRepository) {
		this.purchaseRepository = purchaseRepository;
		this.productRepository = productRepository;
		this.branchRepository = branchRepository;
	}
	
	@Override
	public List<PurchaseResponseDTO> findAllPurchases() {
		// TODO Auto-generated method stub
		List<Purchase> purchases = purchaseRepository.findAll();
		List<PurchaseResponseDTO> purchasesDto = new ArrayList<>();
		
		PurchaseResponseDTO dto;
		for(Purchase p : purchases) {
			dto = Mapper.toDTO(p);
			purchasesDto.add(dto);
		}
		return purchasesDto;
	}

	@Override
	public PurchaseResponseDTO findPurchaseById(Long id) {
		// TODO Auto-generated method stub
		return purchaseRepository.findById(id)
				.map(Mapper::toDTO)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Purchase not found"));
	}

	@Override
	public PurchaseResponseDTO createPurchase(PurchaseCreateDTO purchaseDto) {
		// TODO Auto-generated method stub
		if(purchaseDto == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Purchase invalid");
		if(purchaseDto.getBranchId() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Branch id is required");
		if(purchaseDto.getDetail() == null || purchaseDto.getDetail().isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Purchase Detail must contain at least 1 product");
		
		Branch branch = branchRepository.findById(purchaseDto.getBranchId()).orElse(null);
		if(branch == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Branch not found");
		
		Purchase purchase = new Purchase();
		purchase.setDate(purchaseDto.getDate());
		purchase.setStatus(purchaseDto.getStatus());
		purchase.setBranch(branch);
		
		List<PurchaseDetail> details = new ArrayList<>();
		Double calculatedTotal = 0.0;

		for(PurchaseDetailDTO detDto: purchaseDto.getDetail()) {
			Product product = productRepository.findByName(detDto.getProdName()).orElse(null);
			
			if(product == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: " + detDto.getProdName());
			
			PurchaseDetail purchaseDetail = new PurchaseDetail();
			purchaseDetail.setProduct(product);
			purchaseDetail.setPrice(detDto.getPrice());
			purchaseDetail.setProdQuantity(detDto.getProdQuantity());
			purchaseDetail.setPurchase(purchase);
			details.add(purchaseDetail);
			calculatedTotal += detDto.getPrice()*detDto.getProdQuantity();
		}
		purchase.setDetail(details);
		purchase.setTotal(calculatedTotal);
		
		return Mapper.toDTO(purchaseRepository.save(purchase));
	}

	@Override
	public PurchaseResponseDTO updatePurchase(Long id, PurchaseUpdateDTO purchaseDto) {
		// TODO Auto-generated method stub
		Purchase purchaseToUpdate = purchaseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Purchase not found"));
		
		if(purchaseDto.getDate() != null) {
			purchaseToUpdate.setDate(purchaseDto.getDate());
		}
		
		if(purchaseDto.getStatus() != null) {
			purchaseToUpdate.setStatus(purchaseDto.getStatus());
		}
		
		if(purchaseDto.getTotal() != null) {
			purchaseToUpdate.setTotal(purchaseDto.getTotal());
		}
		
		if(purchaseDto.getBranchId() != null) {
			Branch branch = branchRepository.findById(purchaseDto.getBranchId()).orElse(null);
			if(branch == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Branch not found");
			
			purchaseToUpdate.setBranch(branch);
		}
		
		if(purchaseDto.getDetail() != null || !purchaseDto.getDetail().isEmpty()) {
			purchaseToUpdate.setDetail(purchaseDto.getDetail());
		}
		
		return Mapper.toDTO(purchaseRepository.save(purchaseToUpdate));
	}

	@Override
	public void deletePurchase(Long id) {
		// TODO Auto-generated method stub
		if(!purchaseRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Purchase not found");
		purchaseRepository.deleteById(id);
	}

}
