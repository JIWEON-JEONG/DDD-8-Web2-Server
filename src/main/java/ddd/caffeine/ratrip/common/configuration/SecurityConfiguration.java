package ddd.caffeine.ratrip.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ddd.caffeine.ratrip.common.filter.JwtAuthenticationFilter;
import ddd.caffeine.ratrip.common.filter.JwtExceptionFilter;
import ddd.caffeine.ratrip.common.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
	private final JwtUtil jwtUtil;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests()
			.requestMatchers("/v1/auth/**").permitAll() //auth 관련 요청은 인증 없이 접근을 허용한다. ->
			.and()
			.httpBasic().disable() //사용자 인증방법인 HTTP Basic Authentication을 사용하지 않는다.
			.formLogin().disable() //formLogin 검증 방법은 사용하지 않겠다.
			.logout().disable() //logout 방식은 사용하지 않는다.
			.csrf().disable()
			.cors().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.headers()
			.frameOptions().sameOrigin()
			.httpStrictTransportSecurity().disable()
			.and()
			.addFilterBefore(new JwtAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(new JwtExceptionFilter(), JwtAuthenticationFilter.class)
			.build();
	}

}
