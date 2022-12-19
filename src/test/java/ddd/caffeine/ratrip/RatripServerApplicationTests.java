package ddd.caffeine.ratrip;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@SpringBootTest
@ContextConfiguration(
	classes = RatripServerApplicationTests.class,
	loader = AnnotationConfigContextLoader.class)
class RatripServerApplicationTests {

	@Test
	void contextLoads() {
	}
}
