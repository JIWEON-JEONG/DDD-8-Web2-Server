package ddd.caffeine.ratrip.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ddd.caffeine.ratrip.KakaoFeignDecoder;
import feign.Logger;

@Configuration
public class FeignConfig {

	/**
	 * FULL: request, response의 headers, body 그리고 metaData를 모두 로깅.
	 */
	@Bean
	Logger.Level loggerLevel() {
		return Logger.Level.FULL;
	}

	@Bean
	public KakaoFeignDecoder decoder() {
		return new KakaoFeignDecoder();
	}
}
