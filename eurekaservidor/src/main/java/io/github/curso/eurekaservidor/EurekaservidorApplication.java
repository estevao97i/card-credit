package io.github.curso.eurekaservidor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaservidorApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaservidorApplication.class, args);
	}

}
