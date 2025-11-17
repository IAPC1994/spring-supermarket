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

import com.ivanpanussis.supermarket.dto.branch.BranchCreateDTO;
import com.ivanpanussis.supermarket.dto.branch.BranchResponseDTO;
import com.ivanpanussis.supermarket.dto.branch.BranchUpdateDTO;
import com.ivanpanussis.supermarket.service.BranchService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

	private BranchService branchService;
	
	public BranchController(BranchService branchService) {
		this.branchService = branchService;
	}
	
	@GetMapping
	public ResponseEntity<List<BranchResponseDTO>> findAll(){
		return ResponseEntity.ok(branchService.findBranches());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BranchResponseDTO> findById(@PathVariable Long id){
		return ResponseEntity.ok(branchService.findBranch(id));
	}
	
	@PostMapping
	public ResponseEntity<BranchResponseDTO> create(@Valid @RequestBody BranchCreateDTO dto ){
		BranchResponseDTO createdBranch = branchService.createBranch(dto);
		return ResponseEntity.created(URI.create("/api/branches/" + createdBranch.getId())).body(createdBranch);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<BranchResponseDTO> update(@PathVariable Long id, @Valid @RequestBody BranchUpdateDTO dto){
		return ResponseEntity.ok(branchService.updateBranch(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		branchService.deleteBranch(id);
		return ResponseEntity.noContent().build();
	}
}
