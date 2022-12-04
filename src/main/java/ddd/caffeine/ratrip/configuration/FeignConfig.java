package ddd.caffeine.ratrip.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.Retryer;

@Configuration
public class FeignConfig {

	/**
	 * FULL: request, response의 headers, body 그리고 metaData를 모두 로깅.
	 */
	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

	/**
	 * Retry 정책
	 * - 1초를 시작으로 최대 2초까지 Retry.
	 * - 최대 3번 Retry.
	 */
	@Bean
	public Retryer retryer() {
		return new Retryer.Default(1000, 2000, 3);
	}

	// @Bean
	// public FeignExceptionDecode decoder() {
	// 	return new FeignExceptionDecode();
	// }
}
