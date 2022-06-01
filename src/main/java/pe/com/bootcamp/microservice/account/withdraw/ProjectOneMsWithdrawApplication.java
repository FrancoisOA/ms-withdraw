package pe.com.bootcamp.microservice.account.withdraw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ProjectOneMsWithdrawApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectOneMsWithdrawApplication.class, args);
	}

}