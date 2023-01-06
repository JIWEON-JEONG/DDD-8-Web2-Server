package ddd.caffeine.ratrip.module.place.model.address;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import ddd.caffeine.ratrip.module.place.model.Region;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
public class Address {

	@NotNull
	@Enumerated(EnumType.STRING)
	private Region region;

	@NotNull
	@Column(name = "detailed_address", columnDefinition = "VARCHAR(100)")
	String detailed;

	public Address(Region region, String detailed) {
		this.region = region;
		this.detailed = detailed;
	}
}
