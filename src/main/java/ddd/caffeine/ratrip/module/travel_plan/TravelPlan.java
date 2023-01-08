package ddd.caffeine.ratrip.module.travel_plan;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Transient;

import ddd.caffeine.ratrip.module.Region;
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

	@Column
	@Enumerated(EnumType.STRING)
	private Region region;

	@Column
	private int travelDays;

	/**
	 * getter 같은 메서드 사용시 쿼리 나가지 않는지 확인.
	 *
	 * Todo: 최대 몇명까지 초대 할 수 있는지 정하는게 좋을거같다. 악용방지.
	 */
	@Transient
	private List<User> users;
}
