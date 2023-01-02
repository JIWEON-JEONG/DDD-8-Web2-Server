package ddd.caffeine.ratrip.module.feign.domain.place.kakao.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import ddd.caffeine.ratrip.module.place.model.Place;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Todo : 추후 필요시 distance 추가
 */
@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PlaceKakaoData {
	private String id;
	private String categoryGroupCode;
	private String categoryGroupName;
	private String categoryName;
	private String phone;
	private String placeName;
	private String placeUrl;
	private String roadAddressName;
	private String addressName;
	private String x;
	private String y;

	public Place mapByPlaceEntity() {
		Place place = Place.builder()
			.kakaoId(this.id)
			.name(this.placeName)
			.telephone(this.phone)
			.build();

		place.setPlaceCategory(this.categoryGroupCode);
		place.setAddress(addressName);
		place.setLocation(Double.parseDouble(y), Double.parseDouble(x));

		return place;
	}
}

