package ddd.caffeine.ratrip.module.place.model.address;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
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
