package ir.saeidbabaei.productrecommendationservice.service;

import ir.saeidbabaei.productrecommendationservice.model.Recommendation;

import java.util.List;
import java.util.Optional;

public interface IRecommendationService {
	
  public List<Recommendation> findAll();
  
  public Recommendation save(Recommendation recommendation);

  public void deleteById(Long id);
  
  public Optional<Recommendation> findById(Long id);
  
  public List<Recommendation> findByProductId(Long productId);
  
}
