package ddd.caffeine.ratrip.module.place.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
public class Location {
	@NotNull
	@Column
	private double latitude;

	@NotNull
	@Column
	private double longitude;

	public Location(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
