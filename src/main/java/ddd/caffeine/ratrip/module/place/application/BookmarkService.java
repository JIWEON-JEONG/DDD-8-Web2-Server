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
import ddd.caffeine.ratrip.module.place.domain.repository.dao.BookMarkPlaceDao;
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
		return bookmarkRepository.existsByUserIdAndPlaceId(user.getId(), place.getId());
	}

	public UUID registerBookmark(User user, Place place) {
		Bookmark bookmark = Bookmark.of(user, place);
		return bookmarkRepository.save(bookmark).getId();
	}

	public void releaseBookmark(User user, Place place) {
		Bookmark bookmark = readBookmark(user, place);
		bookmarkValidator.validateExistBookmark(bookmark);
		bookmarkRepository.deleteBookMark(bookmark);
	}

	public BookmarksResponseDto getBookmarks(User user, List<String> categories,
		Pageable page) {
		Slice<BookMarkPlaceDao> bookmarkPlaceDtos = bookmarkRepository.findBookmarkPlacesInCategories(
			Category.createCategories(categories),
			user, page);

		return new BookmarksResponseDto(bookmarkPlaceDtos.getContent(), bookmarkPlaceDtos.hasNext());
	}

	/**
	 * 북마크 엔티티를 조회 하는 메서드.
	 */
	private Bookmark readBookmark(User user, Place place) {
		Bookmark bookmark = bookmarkRepository.findByUserAndPlace(user, place);
		return bookmark;
	}
}
