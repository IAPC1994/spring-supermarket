package com.ivanpanussis.supermarket.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivanpanussis.supermarket.dto.purchase.PurchaseCreateDTO;
import com.ivanpanussis.supermarket.dto.purchase.PurchaseResponseDTO;
import com.ivanpanussis.supermarket.dto.purchase.PurchaseUpdateDTO;
import com.ivanpanussis.supermarket.service.PurchaseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

	private final PurchaseService purchaseService;
	
	public PurchaseController(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}
	
	@GetMapping
	public ResponseEntity<List<PurchaseResponseDTO>> findAll(){
		return ResponseEntity.ok(purchaseService.findAllPurchases());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PurchaseResponseDTO> findById(@PathVariable Long id){
		return ResponseEntity.ok(purchaseService.findPurchaseById(id));
	}
	
	@PostMapping
	public ResponseEntity<PurchaseResponseDTO> create(@Valid @RequestBody PurchaseCreateDTO dto){
		PurchaseResponseDTO createdPurchase = purchaseService.createPurchase(dto);
		return ResponseEntity.created(URI.create("/api/purchases" + createdPurchase.getId())).body(createdPurchase);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<PurchaseResponseDTO> update(@PathVariable Long id, @Valid @RequestBody PurchaseUpdateDTO dto){
		return ResponseEntity.ok(purchaseService.updatePurchase(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		purchaseService.deletePurchase(id);
		return ResponseEntity.noContent().build();
	}
}
