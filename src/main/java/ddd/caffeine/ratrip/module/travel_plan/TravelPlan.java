package ddd.caffeine.ratrip.module.travel_plan;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import ddd.caffeine.ratrip.module.day_schedule.DaySchedule;
import ddd.caffeine.ratrip.module.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class TravelPlan {

	@Id
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	/**
	 * getter 같은 메서드 사용시 쿼리 나가지 않는지 확인.
	 */
	@Transient
	private List<User> users;

	@OneToMany(mappedBy = "day_schedule")
	List<DaySchedule> schedules;
}
