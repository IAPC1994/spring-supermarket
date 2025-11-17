package com.ivanpanussis.supermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivanpanussis.supermarket.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>{

}
