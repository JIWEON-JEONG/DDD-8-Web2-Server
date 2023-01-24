package ddd.caffeine.ratrip.module.travel_plan.presentation.dto.day_schedule;

import javax.validation.constraints.Size;

import ddd.caffeine.ratrip.common.validator.annotation.UUIDFormat;
import lombok.Getter;

//Todo : Memo
@Getter
public class DayScheduleAddPlaceRequestDto {
	@UUIDFormat
	private String placeUUID;
	@Size(max = 255, message = "최대 글자수는 255자 입니다.")
	private String memo;

	public DayScheduleAddPlaceRequestDto(String placeId, String memo) {
		this.placeUUID = placeId;
		this.memo = memo;
	}
}
