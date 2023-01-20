package ddd.caffeine.ratrip.module.travel_plan.domain.repository.dao;

import java.time.LocalDate;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;

@Getter
public class LocalDateDao {
	private LocalDate localDate;

	@QueryProjection
	public LocalDateDao(LocalDate localDate) {
		this.localDate = localDate;
	}

}
