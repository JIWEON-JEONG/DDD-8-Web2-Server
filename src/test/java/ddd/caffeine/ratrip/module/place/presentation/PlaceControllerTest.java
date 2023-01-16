package ddd.caffeine.ratrip.module.place.presentation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;

@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(controllers = PlaceController.class,
	excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class PlaceControllerTest {

	@Test
	void callPlaceDetailsApiByThirdPartyId() {
	}

	@Test
	void callPlaceDetailsApiByUUID() {
	}
}