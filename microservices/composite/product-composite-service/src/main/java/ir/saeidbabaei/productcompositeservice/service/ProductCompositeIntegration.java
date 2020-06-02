package ir.saeidbabaei.productcompositeservice.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

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
    
    private final int productServicePort;
    private final int recommendationServicePort;
    private final int reviewServicePort;

    private RestTemplate restTemplate = new RestTemplate();
    
    @Autowired
    Util util; 
   
    @Autowired
    public ProductCompositeIntegration(
        @Value("${saba.appconfig.servers.product-service.host}") String productService,
        @Value("${saba.appconfig.servers.product-recommendation-service.host}") String recommendationService,
        @Value("${saba.appconfig.servers.product-review-service.host}") String reviewService,
        @Value("${saba.appconfig.servers.product-service.port}") int productServicePort,
        @Value("${saba.appconfig.servers.product-recommendation-service.port}") int recommendationServicePort,
        @Value("${saba.appconfig.servers.product-review-service.port}") int reviewServicePort) {

        this.productService = productService;
        this.recommendationService = recommendationService;
        this.reviewService = reviewService;
        
        this.productServicePort = productServicePort;
        this.recommendationServicePort = recommendationServicePort;
        this.reviewServicePort = reviewServicePort;

    }
    
    public ResponseEntity<Product> getProduct(long id) {

        try {

            String url = productService + ":" + productServicePort + "/api/product/" + id;

            ResponseEntity<String> resultStr = restTemplate.getForEntity(url, String.class);

            Product product = response2Product(resultStr);

            return util.createOkResponse(product);

        } catch (RuntimeException ex) {
            throw ex;
        }

    }


    public ResponseEntity<List<Recommendation>> getRecommendations(long productId) {
        try {

            String url = recommendationService + ":" + recommendationServicePort + "/api/product-recommendation" + "/get-by-product/" + productId;

            ResponseEntity<String> resultStr = restTemplate.getForEntity(url, String.class);

            List<Recommendation> recommendations = response2Recommendations(resultStr);

            return util.createOkResponse(recommendations);

        } catch (RuntimeException ex) {
            throw ex;
        }
    }

    public ResponseEntity<List<Review>> getReviews(long productId) {

        try {

            String url = reviewService + ":" + reviewServicePort + "/api/product-review" + "/get-by-product/" + productId;

            ResponseEntity<String> resultStr = restTemplate.getForEntity(url, String.class);

            List<Review> reviews = response2Reviews(resultStr);
            return util.createOkResponse(reviews);

        } catch (RuntimeException ex) {
            throw ex;
        }
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