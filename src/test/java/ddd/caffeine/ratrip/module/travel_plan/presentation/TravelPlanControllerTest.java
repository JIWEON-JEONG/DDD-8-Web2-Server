package ddd.caffeine.ratrip.module.travel_plan.presentation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelDate;
import ddd.caffeine.ratrip.module.travel_plan.presentation.dto.TravelPlanStartRequestDto;

@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(controllers = TravelPlanController.class,
	excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class TravelPlanControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	@DisplayName("RequestBody 매핑 정상 동작 확인")
	public void mappingRequestBodyTest() throws Exception {
		//given
		String url = "/v1/travel-plan/";
		List<TravelDate> travelDates = List.of(new TravelDate(LocalDate.now()),
			new TravelDate(LocalDate.of(2022, 12, 31)));
		String content = mapper.writeValueAsString(new TravelPlanStartRequestDto("서울", travelDates));

		//when
		ResultActions resultActions = this.mockMvc.perform(
			post(url)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(content));

		//then
		resultActions
			.andExpect(status().isOk());
	}

	@ParameterizedTest
	@DisplayName("RequestBody LocalDate Form Valid 예외 동작 확인")
	@ValueSource(strings = {"2021-10-10-22-10", "20231-32-32", "-10-200"})
	public void localDateValidateFormTest(String date) throws Exception {
		//given
		String url = "/v1/travel-plan/";

		//when
		ResultActions resultActions = this.mockMvc.perform(
			post(url)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("{\"region\":\"서울\",\"travelDates\":[{\"date\":" + date + "}]}"));

		//then
		resultActions
			.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("RequestBody TravelDate 리스트가 Empty 일 경우 예외 동작 확인")
	public void localDateValidateIfEmptyTest() throws Exception {
		//given
		String url = "/v1/travel-plan/";
		List<TravelDate> emptyDates = new ArrayList<>();
		String content = mapper.writeValueAsString(new TravelPlanStartRequestDto("서울", emptyDates));

		//when
		ResultActions resultActions = this.mockMvc.perform(
			post(url)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(content));

		//then
		resultActions
			.andExpect(status().isBadRequest());
	}
}