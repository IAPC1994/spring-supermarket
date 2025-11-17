package com.ivanpanussis.supermarket.service;

import java.util.List;

import com.ivanpanussis.supermarket.dto.branch.BranchCreateDTO;
import com.ivanpanussis.supermarket.dto.branch.BranchResponseDTO;
import com.ivanpanussis.supermarket.dto.branch.BranchUpdateDTO;

public interface IBranchService {

	List<BranchResponseDTO> findBranches();
	BranchResponseDTO findBranch(Long id);
	BranchResponseDTO createBranch(BranchCreateDTO branchDto);
	BranchResponseDTO updateBranch(Long id, BranchUpdateDTO branchDto);
	void deleteBranch(Long id);
}
