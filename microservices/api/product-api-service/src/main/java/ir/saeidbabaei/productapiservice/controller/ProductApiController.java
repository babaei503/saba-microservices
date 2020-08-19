package ir.saeidbabaei.productapiservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import ir.saeidbabaei.productapiservice.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.net.URI;



@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RefreshScope
public class ProductApiController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductApiController.class);
    
    //private RestTemplate restTemplate = new RestTemplate();
	//To make it possible for Spring Cloud Sleuth to add tracing headers to the outgoing requests
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    Util util;
    
    @Value("${saba.appconfig.servers.productCompositeService}") 
    String productCompositeService;

    
    /**
     * Get product composite by product id
     *
     * @param 	productId	Product Id
     * @return				ResponseEntity ProductComposite
     */
    @RequestMapping("/{productId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @HystrixCommand(fallbackMethod = "defaultProductComposite")
    public ResponseEntity<String> getProductComposite(@PathVariable int productId) {
        
    	URI uri= util.getServiceUrl(productCompositeService);

        String url = uri.toString() + "/api/product-composite/" + productId;

        LOG.debug("Get Product Composite from URL: {}", url);
        
        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);

        LOG.info("Get Product Composite http-status: {}", result.getStatusCode());
        LOG.debug("Get Product Composite body: {}", result.getBody());
        
        return result;
    }

    /**
     * Fallback method for getProductComposite()
     *
     * @param 	productId	Product Id
     * @return				ResponseEntity BAD_GATEWAY
     */
    public ResponseEntity<String> defaultProductComposite(
        @PathVariable int productId) {

    	LOG.warn("Using fallback method for product-composite-service.");

        return new ResponseEntity<String>("", HttpStatus.BAD_GATEWAY);
    }
    
}
