package ddd.caffeine.ratrip.module.place.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

	//TODO - 인자를 Enum 타입으로 받는 법 알아보기
	public static List<Category> typeCastStringToCategory(List<String> categories) {
		List<Category> categoryList = new ArrayList<>();
		for (String category : categories) {
			Optional<Category> tmp = Arrays.stream(values()).filter(c -> c.name().equals(category))
				.findFirst();

			tmp.ifPresent(categoryList::add);
		}
		return categoryList;
	}
}
