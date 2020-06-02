package ir.saeidbabaei.productcompositeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ir.saeidbabaei.productcompositeservice.model.Product;
import ir.saeidbabaei.productcompositeservice.model.Recommendation;
import ir.saeidbabaei.productcompositeservice.model.Review;
import ir.saeidbabaei.productcompositeservice.service.IProductCompositeService;
import ir.saeidbabaei.productcompositeservice.util.Util;
import ir.saeidbabaei.productcompositeservice.model.ProductAggregated;


/**
 * 
 * @author Saeid Babaei
 *
 */
@RestController
@RequestMapping("/api/product-composite")
public class ProductCompositeController {

	@Autowired
	IProductCompositeService productCompositeService;
	
    @Autowired
    Util util; 
	
    @RequestMapping("/{id}")
    public ResponseEntity<ProductAggregated> getProductAggregated(@PathVariable long id) {

        Product product = productCompositeService.getBasicProductInfo(id);

        List<Recommendation> recommendations = productCompositeService.getRecommendations(id);

        List<Review> reviews = productCompositeService.getReviews(id);

        return util.createOkResponse(new ProductAggregated(product, recommendations, reviews));
    }   
    
}