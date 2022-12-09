package ddd.caffeine.ratrip.module.user.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ddd.caffeine.ratrip.module.user.domain.SocialInfo;
import ddd.caffeine.ratrip.module.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findUserBySocialInfo(SocialInfo socialInfo);
}
