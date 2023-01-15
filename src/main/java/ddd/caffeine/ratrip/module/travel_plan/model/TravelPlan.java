package ddd.caffeine.ratrip.module.travel_plan.model;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import ddd.caffeine.ratrip.common.jpa.AuditingTimeEntity;
import ddd.caffeine.ratrip.common.model.Region;
import ddd.caffeine.ratrip.common.util.SequentialUUIDGenerator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TravelPlan extends AuditingTimeEntity {

	@Id
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	@NotNull
	@Column
	private String title;

	@NotNull
	@Column
	@Enumerated(EnumType.STRING)
	private Region region;

	@NotNull
	@Column
	private int travelDays;

	@NotNull
	@Column(columnDefinition = "DATE")
	private LocalDate startDate;

	@PrePersist
	public void createPrimaryKey() {
		//sequential uuid 생성
		this.id = SequentialUUIDGenerator.generate();
	}

	@Builder
	public TravelPlan(String title, Region region, int travelDays, LocalDate startDate) {
		this.title = title;
		this.region = region;
		this.travelDays = travelDays;
		this.startDate = startDate;
	}
}
