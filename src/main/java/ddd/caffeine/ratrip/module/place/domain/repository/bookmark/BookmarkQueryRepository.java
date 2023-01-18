package ddd.caffeine.ratrip.module.place.domain.repository.bookmark;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import ddd.caffeine.ratrip.module.bookmark.domain.Bookmark;
import ddd.caffeine.ratrip.module.bookmark.presentation.dto.response.BookmarkPlaceDto;
import ddd.caffeine.ratrip.module.place.domain.Category;
import ddd.caffeine.ratrip.module.place.domain.Place;
import ddd.caffeine.ratrip.module.user.domain.User;

public interface BookmarkQueryRepository {
	long deleteByUserAndPlace(User user, Place place);

	Bookmark findByUserAndPlace(User user, Place place);

	boolean findByUserIdAndPlaceId(UUID userId, UUID placeId);

	Slice<BookmarkPlaceDto> findBookmarkPlacesInCategories(List<Category> categories, User user, Pageable pageable);
}
