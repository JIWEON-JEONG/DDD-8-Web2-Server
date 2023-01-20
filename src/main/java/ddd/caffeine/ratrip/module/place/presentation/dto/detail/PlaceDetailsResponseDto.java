package ddd.caffeine.ratrip.module.place.presentation.dto.detail;

import java.util.UUID;

import ddd.caffeine.ratrip.module.place.domain.Location;
import ddd.caffeine.ratrip.module.place.domain.Place;
import lombok.Builder;
import lombok.Getter;

/**
 * @Todo : 프론트 및 디자인분들과 해당 필드 상의
 */
@Getter
public class PlaceDetailsResponseDto {
	private UUID id;
	private String kakaoId;
	private String name;
	private String category;
	private String address;
	private Location location;
	private boolean isUpdated;
	private String imageLink;
	private String additionalInfoLink;
	private String telephone;

	private boolean isBookMarked;
	private boolean isRegisteredSchedule;

	private int day;
	private String memo;

	public PlaceDetailsResponseDto(Place place, boolean isBookMarked, boolean isRegisteredSchedule) {
		this.id = place.getId();
		this.kakaoId = place.getKakaoId();
		this.name = place.getName();
		this.category = place.getCategory().name();
		this.address = place.getAddress().toString();
		this.location = place.getLocation();
		this.isUpdated = place.isUpdated();
		this.imageLink = place.getImageLink();
		this.additionalInfoLink = place.getAdditionalInfoLink();
		this.telephone = place.getTelephone();
		this.isBookMarked = isBookMarked;
	}

	@Builder
	public PlaceDetailsResponseDto(Place place, boolean isBookMarked, boolean isRegisteredSchedule,
		int day, String memo) {
		this.id = place.getId();
		this.kakaoId = place.getKakaoId();
		this.name = place.getName();
		this.category = place.getCategory().name();
		this.address = place.getAddress().toString();
		this.location = place.getLocation();
		this.isUpdated = place.isUpdated();
		this.imageLink = place.getImageLink();
		this.additionalInfoLink = place.getAdditionalInfoLink();
		this.telephone = place.getTelephone();
		this.isBookMarked = isBookMarked;
		this.isRegisteredSchedule = isRegisteredSchedule;
		this.day = day;
		this.memo = memo;
	}
}
