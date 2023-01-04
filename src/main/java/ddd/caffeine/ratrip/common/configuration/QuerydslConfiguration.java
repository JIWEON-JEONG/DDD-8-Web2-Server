package ddd.caffeine.ratrip.common.configuration;

import org.springframework.context.annotation.Configuration;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Configuration
public class QuerydslConfiguration {
	@PersistenceContext
	private EntityManager entityManager;

	// @Bean
	// public JPAQueryFactory jpaQueryFactory() {
	// 	return new JPAQueryFactory(entityManager);
	// }
}
