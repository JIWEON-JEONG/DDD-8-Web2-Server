package ddd.caffeine.ratrip.module.place.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdatePlaceModel {

	private String name;
	private Category category;
	private Address address;
	private Location location;
	private String telephone;

	@Builder
	public UpdatePlaceModel(String name, Category category, Address address, Location location, String telephone) {
		this.name = name;
		this.category = category;
		this.address = address;
		this.location = location;
		this.telephone = telephone;
	}
}
