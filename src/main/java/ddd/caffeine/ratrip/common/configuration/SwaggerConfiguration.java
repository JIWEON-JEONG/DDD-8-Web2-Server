package ddd.caffeine.ratrip.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {
	@Bean
	public OpenAPI openApi() {
		return new OpenAPI()
			.info(new Info()
				.title("Ratrip API")
				.description("[인증]이 붙은 API는 요청 헤더에 Key: Authorization, Value: Bearer {token}을 포함해야 합니다."));
	}
}
