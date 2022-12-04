package ddd.caffeine.ratrip.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@Configuration
public class FeignConfig {

	/**
	 * FULL : request, response의 headers, body 그리고 metadata를 모두 로깅.
	 */
	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

	// @Bean
	// public ErrorDecoder feignErrorDecoder() {
	//     return new ErrorDecoder() {
	//         @Override
	//         public Exception decode(String methodKey, Response response) {
	//             return null;
	//         }
	//     };
	// }
}
