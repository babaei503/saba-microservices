package ir.saeidbabaei.productrecommendationservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ir.saeidbabaei.productrecommendationservice.model.Recommendation;
import ir.saeidbabaei.productrecommendationservice.repositories.RecommendationRepository;


@Service
public class RecommendationService implements IRecommendationService {

  @Autowired
  RecommendationRepository recommendationRepository;
  
  @Override
  public List<Recommendation> findAll() {
    List<Recommendation> recommendationList = new ArrayList<>();
    recommendationRepository.findAll().forEach(recommendationList::add);
    return recommendationList;
  }
  
  @Override
  public Recommendation save(Recommendation recommendation) {
    return recommendationRepository.save(recommendation);
  }

  @Override
  public Optional<Recommendation> findById(Long id) {
    Optional<Recommendation> recommendation = recommendationRepository.findById(id);
    return recommendation;
  }

  @Override
  public  void deleteById(Long id){
	recommendationRepository.deleteById(id);
  }
  
  @Override
  public List<Recommendation> findByProductId(Long productId) { 
    List<Recommendation> recommendationList = new ArrayList<>();
    recommendationRepository.findByProductId(productId).forEach(recommendationList::add);
    return recommendationList;
  }
  
}
