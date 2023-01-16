package ddd.caffeine.ratrip.module.bookmark.application;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.module.bookmark.domain.Bookmark;
import ddd.caffeine.ratrip.module.bookmark.domain.repository.BookmarkRepository;
import ddd.caffeine.ratrip.module.place.model.Place;
import ddd.caffeine.ratrip.module.place.service.PlaceService;
import ddd.caffeine.ratrip.module.user.domain.User;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {
	private final PlaceService placeService;
	private final BookmarkRepository bookmarkRepository;

	public UUID addBookmark(final UUID placeId, final User user) {
		Place place = placeService.findPlaceById(placeId);
		Bookmark bookmark = bookmarkRepository.save(Bookmark.of(user, place));
		return bookmark.getId();
	}

	public void deleteBookmark(final UUID placeId, final User user) {
		Place place = placeService.findPlaceById(placeId);
		bookmarkRepository.deleteByUserAndPlace(user, place);
	}
}
