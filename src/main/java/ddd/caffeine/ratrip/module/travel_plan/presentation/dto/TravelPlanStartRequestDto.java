package ddd.caffeine.ratrip.module.travel_plan.presentation.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import ddd.caffeine.ratrip.common.model.Region;
import ddd.caffeine.ratrip.module.travel_plan.model.TravelPlan;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TravelPlanStartRequestDto {
	@NotEmpty(message = "Region must not be Blank")
	private String region;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@NotEmpty(message = "날짜는 하루 이상 선택 하셔야합니다.")
	private LocalDate travelStartDate;

	@NotNull(message = "TravelDays must not be Null")
	private int travelDays;

	public TravelPlanStartRequestDto(String region, LocalDate travelStartDate, int travelDays) {
		this.region = region;
		this.travelStartDate = travelStartDate;
		this.travelDays = travelDays;
	}

	public TravelPlan mapByTravelPlan() {
		Region region = Region.createRegionIfNotExistReturnEtc(this.region);
		return TravelPlan.builder()
			//Todo : title UI 에 대해서 디자인 물어보기. (임시 적용)
			.title(region + " " + "여행")
			.startDate(travelStartDate)
			.travelDays(travelDays)
			.build();
	}
}
