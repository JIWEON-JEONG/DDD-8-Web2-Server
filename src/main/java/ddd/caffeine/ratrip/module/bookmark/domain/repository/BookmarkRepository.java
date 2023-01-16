package ddd.caffeine.ratrip.module.bookmark.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ddd.caffeine.ratrip.module.bookmark.domain.Bookmark;
import ddd.caffeine.ratrip.module.place.model.Place;
import ddd.caffeine.ratrip.module.user.domain.User;

public interface BookmarkRepository extends JpaRepository<Bookmark, UUID> {
	Bookmark deleteByUserAndPlace(User user, Place place);
}
