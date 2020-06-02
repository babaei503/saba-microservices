package ir.saeidbabaei.productcompositeservice.service;

import java.util.List;

import ir.saeidbabaei.productcompositeservice.model.Product;
import ir.saeidbabaei.productcompositeservice.model.Recommendation;
import ir.saeidbabaei.productcompositeservice.model.Review;

public interface IProductCompositeService {
	
    public Product getBasicProductInfo(Long id);
    
    public List<Review> getReviews(Long id);
    
    public List<Recommendation> getRecommendations(Long id);
  
}