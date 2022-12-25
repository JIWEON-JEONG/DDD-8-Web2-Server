package ddd.caffeine.ratrip.module.user.domain;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.uuid.Generators;

import ddd.caffeine.ratrip.common.jpa.AuditingTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends AuditingTimeEntity {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserStatus status;

	@Embedded
	private SocialInfo socialInfo;

	@PrePersist
	public void createUserUniqId() {
		//sequential uuid 생성
		UUID uuid = Generators.timeBasedGenerator().generate();
		String[] uuidArr = uuid.toString().split("-");
		String uuidStr = uuidArr[2] + uuidArr[1] + uuidArr[0] + uuidArr[3] + uuidArr[4];
		StringBuilder sb = new StringBuilder(uuidStr);
		sb.insert(8, "-");
		sb.insert(13, "-");
		sb.insert(18, "-");
		sb.insert(23, "-");
		uuid = UUID.fromString(sb.toString());
		this.id = uuid;
	}

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
