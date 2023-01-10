package ddd.caffeine.ratrip.module.travel_plan.presentation.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TravelPlanStartRequestDto {
	@NotNull
	private String region;

	@NotEmpty(message = "날짜는 하루 이상 선택 하셔야합니다.")
	private List<@Valid TravelDate> travelDates;

	public TravelPlanStartRequestDto(String region, List<TravelDate> travelDates) {
		this.region = region;
		this.travelDates = travelDates;
	}
}
