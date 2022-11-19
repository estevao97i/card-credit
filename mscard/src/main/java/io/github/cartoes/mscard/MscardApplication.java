package io.github.cartoes.mscard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MscardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscardApplication.class, args);
	}

}
