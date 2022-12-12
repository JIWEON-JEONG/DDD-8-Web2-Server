package ddd.caffeine.ratrip.module.user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ddd.caffeine.ratrip.module.user.domain.SocialInfo;
import ddd.caffeine.ratrip.module.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findUserBySocialInfo(SocialInfo socialInfo);
}
