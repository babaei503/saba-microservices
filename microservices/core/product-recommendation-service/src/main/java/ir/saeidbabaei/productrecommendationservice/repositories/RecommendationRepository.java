package ir.saeidbabaei.productrecommendationservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ir.saeidbabaei.productrecommendationservice.model.Recommendation;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

	List<Recommendation> findByProductId(Long productId);
	
}