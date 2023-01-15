package ddd.caffeine.ratrip.module.travel_plan.presentation.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import ddd.caffeine.ratrip.common.model.Region;
import ddd.caffeine.ratrip.module.travel_plan.model.TravelPlan;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TravelPlanStartRequestDto {
	@NotEmpty
	private String region;

	@NotEmpty(message = "날짜는 하루 이상 선택 하셔야합니다.")
	private List<@Valid TravelDate> travelDates;

	public TravelPlanStartRequestDto(String region, List<TravelDate> travelDates) {
		this.region = region;
		this.travelDates = travelDates;
	}

	public TravelPlan mapByTravelPlan() {
		Region region = Region.createRegionIfNotExistReturnEtc(this.region);
		return TravelPlan.builder().build();
	}
}
