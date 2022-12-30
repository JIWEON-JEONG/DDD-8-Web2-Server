package ddd.caffeine.ratrip.module.place.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class UpdatePlaceModel {

	private String name;
	private Category category;
	private Address address;
	private Location location;
	private String telephone;

}
