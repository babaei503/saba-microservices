package ir.saeidbabaei.productrecommendationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductRecommendationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductRecommendationServiceApplication.class, args);
	}

}
