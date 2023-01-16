package ddd.caffeine.ratrip.module.bookmark.domain.repository;

import ddd.caffeine.ratrip.module.place.model.Place;
import ddd.caffeine.ratrip.module.user.domain.User;

public interface BookmarkQueryRepository {
	long deleteByUserAndPlace(User user, Place place);
}
