package ddd.caffeine.ratrip.module.travel_plan.model;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import ddd.caffeine.ratrip.common.util.SequentialUUIDGenerator;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class DaySchedule {
	@Id
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	@Column(columnDefinition = "DATE")
	private LocalDate date;

	//Todo : 타입 고민.
	@Column(columnDefinition = "VARCHAR(255)")
	private String memo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "travel_plan_id", columnDefinition = "BINARY(16)")
	private TravelPlan travelPlan;

	@PrePersist
	public void createDaySchedulePrimaryKey() {
		//sequential uuid 생성
		this.id = SequentialUUIDGenerator.generate();
	}

	@Builder
	public DaySchedule(LocalDate date, TravelPlan travelPlan) {
		this.date = date;
		this.travelPlan = travelPlan;
	}
}
