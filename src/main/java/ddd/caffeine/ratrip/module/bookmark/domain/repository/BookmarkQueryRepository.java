package ddd.caffeine.ratrip.module.bookmark.domain.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import ddd.caffeine.ratrip.module.bookmark.domain.Bookmark;
import ddd.caffeine.ratrip.module.bookmark.presentation.dto.response.BookmarkPlaceDto;
import ddd.caffeine.ratrip.module.place.model.Category;
import ddd.caffeine.ratrip.module.place.model.Place;
import ddd.caffeine.ratrip.module.user.domain.User;

public interface BookmarkQueryRepository {
	long deleteByUserAndPlace(User user, Place place);

	Bookmark findByPlaceAndUser(User user, Place place);

	Slice<BookmarkPlaceDto> findBookmarkPlacesInCategories(List<Category> categories, User user, Pageable pageable);
}
