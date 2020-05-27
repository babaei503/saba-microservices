package ir.saeidbabaei.productreviewservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ir.saeidbabaei.productreviewservice.model.Review;
import ir.saeidbabaei.productreviewservice.repositories.ReviewRepository;


@Service
public class ReviewService implements IReviewService {

  @Autowired
  ReviewRepository reviewRepository;
  
  @Override
  public List<Review> findAll() {
    List<Review> reviewList = new ArrayList<>();
    reviewRepository.findAll().forEach(reviewList::add);
    return reviewList;
  }
  
  @Override
  public Review save(Review review) {
    return reviewRepository.save(review);
  }

  @Override
  public Optional<Review> findById(Long id) {
    Optional<Review> review = reviewRepository.findById(id);
    return review;
  }

  @Override
  public  void deleteById(Long id){
	reviewRepository.deleteById(id);
  }
  
  @Override
  public List<Review> findByProductId(Long productId) { 
    List<Review> reviewList = new ArrayList<>();
    reviewRepository.findByProductId(productId).forEach(reviewList::add);
    return reviewList;
  }
  
}
