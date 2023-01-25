package ddd.caffeine.ratrip.module.travel_plan.presentation.dto.day_schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ddd.caffeine.ratrip.common.validator.annotation.UUIDFormat;
import lombok.Getter;

@Getter
public class DayScheduleExchangePlaceOrderDto {
	@UUIDFormat
	private String firstPlaceUUID;
	@UUIDFormat
	private String secondPlaceUUID;

	public DayScheduleExchangePlaceOrderDto(String firstPlaceId, String secondPlaceId) {
		this.firstPlaceUUID = firstPlaceId;
		this.secondPlaceUUID = secondPlaceId;
	}

	public List<UUID> readPlaceUUIDs() {
		List<UUID> uuids = new ArrayList<>();
		uuids.add(UUID.fromString(firstPlaceUUID));
		uuids.add(UUID.fromString(secondPlaceUUID));

		return uuids;
	}
}
