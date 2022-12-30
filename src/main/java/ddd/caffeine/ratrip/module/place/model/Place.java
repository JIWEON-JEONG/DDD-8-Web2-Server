package ddd.caffeine.ratrip.module.place.model;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import ddd.caffeine.ratrip.common.util.SequentialUUIDGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 장소 도메인.
 * TODO: field columnDefinition 상의 하여 정할 것.
 */
@Getter
@Entity
@NoArgsConstructor
public class Place {

	@Id
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	@NotNull
	@Column
	private String kakaoId;

	@NotNull
	@Column(columnDefinition = "VARCHAR(100)")
	private String name;

	@Enumerated(EnumType.STRING)
	private Category category;

	@NotNull
	@Embedded
	private Address address;

	@NotNull
	@Embedded
	private Location location;

	@NotNull
	@Column(name = "is_deleted", columnDefinition = "TINYINT(1)")
	private boolean isDeleted = false;

	@Column(columnDefinition = "VARCHAR(100)")
	private String imageLink;

	@Column(columnDefinition = "VARCHAR(100)")
	private String telephone;

	@PrePersist
	public void createPlacePrimaryKey() {
		//sequential uuid 생성
		this.id = SequentialUUIDGenerator.generate();
	}

	public void injectImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public void createLocation(double latitude, double longitude) {
		this.location = new Location(latitude, longitude);
	}

	public void createAddress(String address) {
		this.address = new Address(address);
	}

	public void injectPlaceCategory(String categoryCode) {
		Optional<Category> optionalCategory = Arrays.stream(Category.values())
			.filter((category) -> category.getCode().equals(categoryCode))
			.findFirst();

		this.category = optionalCategory.orElse(Category.기타);
	}

	@Builder
	public Place(String kakaoId, String name, String telephone) {
		this.kakaoId = kakaoId;
		this.name = name;
		this.telephone = telephone;
	}
}
