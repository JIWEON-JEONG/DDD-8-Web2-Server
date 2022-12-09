package ddd.caffeine.ratrip.module.user.domain;

import ddd.caffeine.ratrip.jpa.AuditingTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends AuditingTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserStatus status;

	@Embedded
	private SocialInfo socialInfo;

	@Builder(access = AccessLevel.PACKAGE)
	private User(String name, String email, UserStatus status, String socialId, UserSocialType socialType) {
		this.name = name;
		this.email = email;
		this.status = status;
		this.socialInfo = SocialInfo.of(socialId, socialType);
	}

	public static User of(String name, String email, UserStatus status, String socialId, UserSocialType socialType) {
		return User.builder()
			.name(name)
			.email(email)
			.status(status)
			.socialId(socialId)
			.socialType(socialType)
			.build();
	}
}
