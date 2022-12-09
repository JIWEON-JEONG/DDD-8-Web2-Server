package ddd.caffeine.ratrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableFeignClients
@SpringBootApplication
public class RatripServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatripServerApplication.class, args);
	}

}
