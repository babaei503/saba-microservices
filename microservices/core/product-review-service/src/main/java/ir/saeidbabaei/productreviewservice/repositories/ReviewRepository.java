package ir.saeidbabaei.productreviewservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ir.saeidbabaei.productreviewservice.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	List<Review> findByProductId(Long productId);
	
}