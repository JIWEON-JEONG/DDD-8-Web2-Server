package ddd.caffeine.ratrip.module.place.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Builder;

@Builder
public class ThirdPartyDetailSearchOption {
	private String id;

	private String placeName;

	private String address;

	public Map<String, String> readPlaceNameAndAddress() {
		HashMap<String, String> placeNameAndAddressMap = new HashMap<>();
		placeNameAndAddressMap.put("name", this.placeName);
		placeNameAndAddressMap.put("address", this.address);

		return placeNameAndAddressMap;
	}

	public String readThirdPartyId() {
		return this.id;
	}
}
