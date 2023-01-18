package ddd.caffeine.ratrip.module.place.application;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.module.place.domain.Bookmark;
import ddd.caffeine.ratrip.module.place.domain.Category;
import ddd.caffeine.ratrip.module.place.domain.Place;
import ddd.caffeine.ratrip.module.place.domain.repository.bookmark.BookmarkRepository;
import ddd.caffeine.ratrip.module.place.domain.repository.dao.BookmarkPlaceDao;
import ddd.caffeine.ratrip.module.place.presentation.dto.bookmark.BookmarksResponseDto;
import ddd.caffeine.ratrip.module.user.domain.User;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {
	private final BookmarkRepository bookmarkRepository;
	private final BookmarkValidator bookmarkValidator;

	public boolean isBookmarked(User user, Place place) {
		return bookmarkRepository.existsByUserIdAndPlaceId(place.getId(), user.getId());
	}

	public UUID addBookmark(User user, Place place) {
		Bookmark bookmark = Bookmark.of(user, place);
		return bookmarkRepository.save(bookmark).getId();
	}

	public void deleteBookmark(User user, Place place) {
		Bookmark bookmark = readBookmark(user, place);
		bookmarkValidator.validateExistBookmark(bookmark);
		bookmarkRepository.delete(bookmark);
	}

	@Transactional(readOnly = true)
	public BookmarksResponseDto getBookmarks(User user, List<String> categories,
		Pageable page) {
		Slice<BookmarkPlaceDao> bookmarkPlaceDtos = bookmarkRepository.findBookmarkPlacesInCategories(
			Category.createCategories(categories),
			user, page);

		return new BookmarksResponseDto(bookmarkPlaceDtos.getContent(), bookmarkPlaceDtos.hasNext());
	}

	private Bookmark readBookmark(User user, Place place) {
		Bookmark bookmark = bookmarkRepository.findByUserAndPlace(user, place);
		bookmarkValidator.validateExistBookmark(bookmark);

		return bookmark;
	}
}
