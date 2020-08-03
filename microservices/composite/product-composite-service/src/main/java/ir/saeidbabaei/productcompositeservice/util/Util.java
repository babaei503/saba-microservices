package ir.saeidbabaei.productcompositeservice.util;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


/**
 * 
 * @author Saeid Babaei
 *
 */
@Component
public class Util {

    @Autowired
    private LoadBalancerClient loadBalancer;
   
    /**
     * Find a service instance and return its URI to the service consumer
     *
     * @param		serviceName		The name of the service to lookup
     * @return						Service URI
     */
   public URI getServiceUrl(String serviceName) {
       return getServiceUrl(serviceName, null);
   }

    /**
     * Find a service instance and return its URI to the service consumer
     *
     * @param		serviceName		The name of the service to lookup
     * @param		fallbackUri		Fallback URI if Eureka not available
     * @return						Service URI
     */
    public URI getServiceUrl(String serviceName, String fallbackUri) {
        URI uri = null;
        try {
            ServiceInstance instance = loadBalancer.choose(serviceName);
            uri = instance.getUri();

        } catch (RuntimeException e) {
            // Eureka not available. use fallback.
            uri = URI.create(fallbackUri);
        }

        return uri;
    }
    
    public <T> ResponseEntity<T> createOkResponse(T body) {
        return createResponse(body, HttpStatus.OK);
    }

    public <T> ResponseEntity<T> createResponse(T body, HttpStatus httpStatus) {
        return new ResponseEntity<>(body, httpStatus);
    }
    
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
    
}
