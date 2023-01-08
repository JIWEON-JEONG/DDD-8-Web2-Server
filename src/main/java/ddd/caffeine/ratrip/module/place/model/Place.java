package ddd.caffeine.ratrip.module.place.model;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import ddd.caffeine.ratrip.common.util.SequentialUUIDGenerator;
import ddd.caffeine.ratrip.module.place.feign.kakao.model.PlaceKakaoData;
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
	private boolean isDeleted;

	@NotNull
	@Column(name = "is_updated", columnDefinition = "TINYINT(1)")
	private boolean isUpdated;

	@Column(columnDefinition = "VARCHAR(100)")
	private String imageLink;

	@Column(columnDefinition = "VARCHAR(100)")
	private String telephone;

	@Column
	private int numberOfTrips;

	public void travelCome() {
		this.numberOfTrips++;
	}

	@PrePersist
	public void createPlacePrimaryKey() {
		//sequential uuid 생성
		this.id = SequentialUUIDGenerator.generate();
	}

	public void injectImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public void setLocation(double latitude, double longitude) {
		this.location = new Location(latitude, longitude);
	}

	public void setAddress(String address) {
		this.address = new Address(address);
	}

	public void setPlaceCategory(String categoryCode) {
		Optional<Category> optionalCategory = Arrays.stream(Category.values())
			.filter((category) -> category.getCode().equals(categoryCode))
			.findFirst();

		this.category = optionalCategory.orElse(Category.ETC);
	}

	/**
	 * 장소에 대한 데이터를 업데이트 해야하는지 확인 하는 메서드.
	 */
	public boolean checkNeedsUpdate(String address, String placeName) {
		Address checkSample = new Address(address);
		if (this.name.equals(placeName) || this.address.equals(checkSample)) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	public void update(PlaceKakaoData data) {
		this.isUpdated = Boolean.TRUE;

		this.kakaoId = data.getId();
		this.name = data.getPlaceName();
		this.telephone = data.getPhone();

		setPlaceCategory(data.getCategoryGroupCode());
		setAddress(data.getAddressName());
		setLocation(Double.parseDouble(data.getY()), Double.parseDouble(data.getX()));
	}

	@Builder
	public Place(String kakaoId, String name, String telephone) {
		this.kakaoId = kakaoId;
		this.name = name;
		this.telephone = telephone;
		this.isDeleted = false;
		this.isUpdated = false;
		this.numberOfTrips = 0;
	}
}
