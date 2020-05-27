package ir.saeidbabaei.productreviewservice.service;

import ir.saeidbabaei.productreviewservice.model.Review;

import java.util.List;
import java.util.Optional;

public interface IReviewService {
	
  public List<Review> findAll();
  
  public Review save(Review review);

  public void deleteById(Long id);
  
  public Optional<Review> findById(Long id);
  
  public List<Review> findByProductId(Long productId);
  
}
