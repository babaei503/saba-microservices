package ir.saeidbabaei.dashboardservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableDiscoveryClient
@Controller
@EnableHystrixDashboard
public class DashboardServiceApplication {

	@RequestMapping("/")
	public String home() {
		return "forward:/hystrix";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DashboardServiceApplication.class, args);
	}

}
