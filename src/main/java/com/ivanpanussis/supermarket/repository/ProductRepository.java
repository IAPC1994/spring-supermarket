package com.ivanpanussis.supermarket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivanpanussis.supermarket.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	Optional<Product> findByName(String name);
}
