package ddd.caffeine.ratrip.module.notification.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import ddd.caffeine.ratrip.common.jpa.AuditingTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification extends AuditingTimeEntity {
	@Id
	@GeneratedValue
	private Long id;

	@Column(columnDefinition = "VARCHAR(100)")
	private String title;

	@Column(columnDefinition = "VARCHAR(5000)")
	private String content;
}
