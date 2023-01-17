package ddd.caffeine.ratrip.module.place.presentation.dto.detail;

import javax.validation.constraints.NotBlank;

import ddd.caffeine.ratrip.module.place.model.ThirdPartyDetailSearchOption;
import lombok.Getter;

@Getter
public class PlaceDetailsByThirdPartyRequestDto {

	@NotBlank(message = "PlaceName must not be blank")
	private String placeName;

	@NotBlank(message = "Address must not be blank")
	private String address;

	public PlaceDetailsByThirdPartyRequestDto(String thirdPartyId, String placeName,
		String address) {
		validateParameters(address);
		this.placeName = placeName;
		this.address = address;
	}

	public ThirdPartyDetailSearchOption mapByThirdPartyDetailSearchOption(String thirdPartyId) {
		return ThirdPartyDetailSearchOption.builder()
			.id(thirdPartyId)
			.placeName(this.placeName)
			.address(this.address)
			.build();
	}

	private void validateParameters(String address) {
		//@ToDo : 지번주소인지 도로명주소인지 정하기 -> 01.11 회의 추후 정해서 알려주신다고 함.
	}
}
