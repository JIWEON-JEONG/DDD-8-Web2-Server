package ddd.caffeine.ratrip.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ddd.caffeine.ratrip.common.util.FeignErrorDecoder;
import ddd.caffeine.ratrip.common.util.FeignResponseEncoder;
import feign.Logger;
import feign.Retryer;

@Configuration
public class FeignConfiguration {

	/**
	 * FULL: request, response의 headers, body 그리고 metaData를 모두 로깅.
	 */
	@Bean
	Logger.Level loggerLevel() {
		return Logger.Level.FULL;
	}

	@Bean
	public FeignErrorDecoder errorDecoder(FeignResponseEncoder feignResponseEncoder) {
		return new FeignErrorDecoder(feignResponseEncoder);
	}

	/**
	 * 재시도는 1초를 시작으로 최대 2초로 재시도 하고, 최대 3번으로 재시도.
	 *
	 * @return
	 */
	@Bean
	public Retryer retryer() {
		final long period = 1000L;
		final long maxPeriod = 2000L;
		final int maxAttempts = 3;

		return new Retryer.Default(period, maxPeriod, maxAttempts);
	}

}
