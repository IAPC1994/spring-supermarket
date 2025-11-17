package com.ivanpanussis.supermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivanpanussis.supermarket.model.Branch;

public interface BranchRepository extends JpaRepository <Branch, Long>{
	
}
