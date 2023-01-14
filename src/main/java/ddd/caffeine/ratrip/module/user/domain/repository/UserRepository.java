package ddd.caffeine.ratrip.module.user.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ddd.caffeine.ratrip.module.user.domain.SocialInfo;
import ddd.caffeine.ratrip.module.user.domain.User;

public interface UserRepository extends JpaRepository<User, UUID> {
	User findUserBySocialInfo(SocialInfo socialInfo);

	User findUserById(UUID id); //TEST METHOD
}
