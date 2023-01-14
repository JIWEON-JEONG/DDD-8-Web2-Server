package ddd.caffeine.ratrip.module.travel_plan;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import ddd.caffeine.ratrip.common.model.Region;
import ddd.caffeine.ratrip.common.util.SequentialUUIDGenerator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TravelPlan {

	@Id
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	@Column
	@Enumerated(EnumType.STRING)
	private Region region;

	@Column
	private int travelDays;

	@PrePersist
	public void createPrimaryKey() {
		//sequential uuid 생성
		this.id = SequentialUUIDGenerator.generate();
	}

	@Builder(access = AccessLevel.PACKAGE)
	public TravelPlan(Region region, int travelDays) {
		this.region = region;
		this.travelDays = travelDays;
	}
}
