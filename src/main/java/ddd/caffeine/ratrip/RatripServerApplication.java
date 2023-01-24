package ddd.caffeine.ratrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@EnableJpaAuditing
@EnableFeignClients
@SpringBootApplication
@RestController
@OpenAPIDefinition(servers = {@Server(url = "https://ratrip.store", description = "Default Server URL")})
public class RatripServerApplication {
	@GetMapping("/health-check")
	public ResponseEntity<String> test() {
		return ResponseEntity.ok("health check success");
	}

	public static void main(String[] args) {
		SpringApplication.run(RatripServerApplication.class, args);
	}

}
