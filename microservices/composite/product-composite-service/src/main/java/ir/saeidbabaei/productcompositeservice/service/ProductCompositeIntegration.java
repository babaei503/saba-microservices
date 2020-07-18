package ir.saeidbabaei.productcompositeservice.service;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import ir.saeidbabaei.productcompositeservice.model.Product;
import ir.saeidbabaei.productcompositeservice.model.Recommendation;
import ir.saeidbabaei.productcompositeservice.model.Review;
import ir.saeidbabaei.productcompositeservice.util.Util;


/**
 * 
 * @author Saeid Babaei
 *
 */
@Component
public class ProductCompositeIntegration {

    private final String productService;
    private final String recommendationService;
    private final String reviewService;
    
    private RestTemplate restTemplate = new RestTemplate();
    
    @Autowired
    Util util; 
    
    @Autowired
    public ProductCompositeIntegration(
            @Value("${saba.appconfig.servers.product}") String productService,
            @Value("${saba.appconfig.servers.recommendation}") String recommendationService,
            @Value("${saba.appconfig.servers.review}") String reviewService) {

            this.productService = productService;
            this.recommendationService = recommendationService;
            this.reviewService = reviewService;
    }

    /**
     * Get product info by id
     *
     * @param		id		Product Id
     * @return				Product
     */
    @HystrixCommand(fallbackMethod = "defaultProduct")
    public ResponseEntity<Product> getProduct(long id) {

        try {

        	URI uri = util.getServiceUrl(productService);
        	
            String url = uri.toString() + "/api/product/" + id;

            ResponseEntity<String> resultStr = restTemplate.getForEntity(url, String.class);

            Product product = response2Product(resultStr);

            return util.createOkResponse(product);

        } catch (RuntimeException ex) {
            throw ex;
        }

    }
    

    /**
     * Fallback method for getProduct()
     *
     * @param		id		Product Id
     * @return				ResponseEntity BAD_GATEWAY
     */
    public ResponseEntity<Product> defaultProduct(long id) {
    	
        return util.createResponse(null, HttpStatus.BAD_GATEWAY);
        
    }


    /**
     * Get recommendations of product
     *
     * @param		productId	Product Id	
     * @return					ResponseEntity BAD_GATEWAY
     */
    @HystrixCommand(fallbackMethod = "defaultRecommendations")
    public ResponseEntity<List<Recommendation>> getRecommendations(long productId) {
        try {

        	URI uri = util.getServiceUrl(recommendationService);
        	
            String url = uri.toString() + "/api/product-recommendation" + "/get-by-product/" + productId;

            ResponseEntity<String> resultStr = restTemplate.getForEntity(url, String.class);

            List<Recommendation> recommendations = response2Recommendations(resultStr);

            return util.createOkResponse(recommendations);

        } catch (RuntimeException ex) {
            throw ex;
        }
    }

    
    /**
     * Fallback method for getRecommendations()
     *
     * @param		productId	Product Id	
     * @return					ResponseEntity BAD_GATEWAY
     */
    public ResponseEntity<List<Recommendation>> defaultRecommendations(long productId) {

        return util.createResponse(null, HttpStatus.BAD_GATEWAY);
        
    }
    
 
    /**
     * Get reviews of product
     *
     * @param		productId	Product Id	
     * @return					ResponseEntity BAD_GATEWAY
     */
    @HystrixCommand(fallbackMethod = "defaultReviews")
    public ResponseEntity<List<Review>> getReviews(long productId) {

        try {

        	URI uri = util.getServiceUrl(reviewService);
        	
            String url = uri.toString() + "/api/product-review" + "/get-by-product/" + productId;

            ResponseEntity<String> resultStr = restTemplate.getForEntity(url, String.class);

            List<Review> reviews = response2Reviews(resultStr);
            return util.createOkResponse(reviews);

        } catch (RuntimeException ex) {
            throw ex;
        }
    }

    
    /**
     * Fallback method for getReviews()
     *
     * @param		productId	Product Id	
     * @return					ResponseEntity BAD_GATEWAY
     */
    public ResponseEntity<List<Review>> defaultReviews(long productId) {

        return util.createResponse(null, HttpStatus.BAD_GATEWAY);
        
    }
    
    
    private ObjectReader productReader = null;
    private ObjectReader getProductReader() {

        if (productReader != null) return productReader;

        ObjectMapper mapper = new ObjectMapper();
        return productReader = mapper.readerFor(Product.class);
    }


    private Product response2Product(ResponseEntity<String> response) {
        try {
            return getProductReader().readValue(response.getBody());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Recommendation> response2Recommendations(ResponseEntity<String> response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Recommendation> recommendations = mapper.readValue(response.getBody(), new TypeReference<List<Recommendation>>() {});
            return recommendations;

        } catch (IOException e) {
            throw new RuntimeException(e);

        } catch (RuntimeException re) {
            throw re;
        }
    }

    private List<Review> response2Reviews(ResponseEntity<String> response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Review> reviews = mapper.readValue(response.getBody(), new TypeReference<List<Review>>() {});
            return reviews;

        } catch (IOException e) {
            throw new RuntimeException(e);

        } catch (RuntimeException re) {
            throw re;
        }
    }
    
}