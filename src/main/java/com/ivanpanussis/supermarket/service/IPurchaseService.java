package com.ivanpanussis.supermarket.service;

import java.util.List;

import com.ivanpanussis.supermarket.dto.purchase.PurchaseCreateDTO;
import com.ivanpanussis.supermarket.dto.purchase.PurchaseResponseDTO;
import com.ivanpanussis.supermarket.dto.purchase.PurchaseUpdateDTO;

public interface IPurchaseService {
	List<PurchaseResponseDTO> findAllPurchases();
	PurchaseResponseDTO findPurchaseById(Long id);
	PurchaseResponseDTO createPurchase(PurchaseCreateDTO purchaseDto);
	PurchaseResponseDTO updatePurchase(Long id, PurchaseUpdateDTO purchaseDto);
	void deletePurchase(Long id);
}
