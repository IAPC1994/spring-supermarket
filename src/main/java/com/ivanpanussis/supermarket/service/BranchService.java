package com.ivanpanussis.supermarket.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ivanpanussis.supermarket.dto.branch.BranchCreateDTO;
import com.ivanpanussis.supermarket.dto.branch.BranchResponseDTO;
import com.ivanpanussis.supermarket.dto.branch.BranchUpdateDTO;
import com.ivanpanussis.supermarket.mapper.Mapper;
import com.ivanpanussis.supermarket.model.Branch;
import com.ivanpanussis.supermarket.repository.BranchRepository;

@Service
public class BranchService implements IBranchService{
	
	private final BranchRepository branchRepository;
	
	public BranchService(BranchRepository branchRepository) {
		this.branchRepository = branchRepository;
	}

	@Override
	public List<BranchResponseDTO> findBranches() {
		// TODO Auto-generated method stub
		return branchRepository.findAll()
				.stream()
				.map(Mapper::toDTO)
				.toList();
	}

	@Override
	public BranchResponseDTO findBranch(Long id) {
		// TODO Auto-generated method stub
		return branchRepository.findById(id)
				.map(Mapper::toDTO)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Branch not found"));
	}

	@Override
	public BranchResponseDTO createBranch(BranchCreateDTO branchDto) {
		// TODO Auto-generated method stub
		var branch = Branch.builder()
				.name(branchDto.getName())
				.address(branchDto.getAddress())
				.build();
		return Mapper.toDTO(branchRepository.save(branch));
	}

	@Override
	public BranchResponseDTO updateBranch(Long id, BranchUpdateDTO branchDto) {
		// TODO Auto-generated method stub
		Branch branchToUpdate = branchRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Branch not found"));
		
		if(branchDto.getName() != null) {
			branchToUpdate.setName(branchDto.getName());
		}
		
		if(branchDto.getAddress() != null) {
			branchToUpdate.setAddress(branchDto.getAddress());
		}
		
		return Mapper.toDTO(branchRepository.save(branchToUpdate));
	}

	@Override
	public void deleteBranch(Long id) {
		// TODO Auto-generated method stub
		if(!branchRepository.existsById(id)) 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Branch not found");
		
		branchRepository.deleteById(id);
	}

}
