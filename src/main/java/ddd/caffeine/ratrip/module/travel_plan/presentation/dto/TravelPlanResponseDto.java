package ddd.caffeine.ratrip.module.travel_plan.presentation.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Todo : 이미지 ?
@Getter
@NoArgsConstructor
public class TravelPlanResponseDto {

	List<TravelPlanResponseModel> contents;
	private boolean hasNext;

	@Builder
	public TravelPlanResponseDto(List<TravelPlanResponseModel> contents, boolean hasNext) {
		this.contents = contents;
		this.hasNext = hasNext;
	}
}
