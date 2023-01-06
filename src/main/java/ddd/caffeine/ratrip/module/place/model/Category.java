package ddd.caffeine.ratrip.module.place.model;

import lombok.Getter;

/**
 * 장소에 대한 카테고리를 나타내는 enum 클래스.
 *
 * @Todo : 카테고리 코드 없을 가능성 생각.
 */
@Getter
public enum Category {
	CAFE("CE7"),
	RESTAURANT("FD6"),
	TOURIST_ATTRACTION("AT4"),
	PUBLIC_INSTITUTION("PO3"),
	ACCOMMODATION("AD5"),
	HOSPITAL("HP8"),
	PHARMACY("PM9"),
	KINDERGARTEN("PS3"),
	SCHOOL("SC4"),
	ACADEMY("AC5"),
	PARKING_LOT("PK6"),
	GAS_STATION("OL7"),
	SUBWAY_STATION("SW8"),
	BANK("BK9"),
	CULTURAL_FACILITIES("CT1"),
	BROKERAGE("AG2"),
	SUPERMARKET("MT1"),
	CONVENIENCE_STORE("CS2"),
	ETC("ETC");

	private String code;

	Category(String code) {
		this.code = code;
	}
}
