package ir.saeidbabaei.productservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ir.saeidbabaei.productservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
}