package ir.saeidbabaei.productcompositeservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ir.saeidbabaei.productcompositeservice.model.Product;
import ir.saeidbabaei.productcompositeservice.model.Recommendation;
import ir.saeidbabaei.productcompositeservice.model.Review;


/**
 * 
 * @author Saeid Babaei
 *
 */
@Service
public class ProductCompositeService implements IProductCompositeService {

	@Autowired
	ProductCompositeIntegration productCompositeIntegration;
	
    
    public Product getBasicProductInfo(Long id) {
        ResponseEntity<Product> productResult = productCompositeIntegration.getProduct(id);
        Product product = null;
        if (!productResult.getStatusCode().is2xxSuccessful()) {
            // Something went wrong with getProduct, skip the product-information in the response
        } else {
            product = productResult.getBody();
        }
        return product;
    }


    public List<Review> getReviews(Long id) {
        ResponseEntity<List<Review>> reviewsResult = productCompositeIntegration.getReviews(id);
        List<Review> reviews = null;
        if (!reviewsResult.getStatusCode().is2xxSuccessful()) {
            // Something went wrong with getReviews, skip the review-information in the response
        } else {
            reviews = reviewsResult.getBody();
        }
        return reviews;
    }

    public List<Recommendation> getRecommendations(Long id) {
        ResponseEntity<List<Recommendation>> recommendationResult = productCompositeIntegration.getRecommendations(id);
        List<Recommendation> recommendations = null;
        if (!recommendationResult.getStatusCode().is2xxSuccessful()) {
            // Something went wrong with getRecommendations, skip the recommendation-information in the response
        } else {
            recommendations = recommendationResult.getBody();
        }
        return recommendations;
    }
    
}