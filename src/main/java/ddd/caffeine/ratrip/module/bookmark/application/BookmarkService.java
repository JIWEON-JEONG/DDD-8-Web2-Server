package ddd.caffeine.ratrip.module.bookmark.application;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.module.bookmark.domain.Bookmark;
import ddd.caffeine.ratrip.module.bookmark.domain.repository.BookmarkRepository;
import ddd.caffeine.ratrip.module.place.model.Place;
import ddd.caffeine.ratrip.module.place.service.PlaceService;
import ddd.caffeine.ratrip.module.user.application.UserService;
import ddd.caffeine.ratrip.module.user.domain.User;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {
	private final UserService userService;
	private final PlaceService placeService;
	private final BookmarkRepository bookmarkRepository;

	public UUID addBookmark(final UUID placeId) {
		Place place = placeService.findPlaceById(placeId);
		User user = userService.findUserById(UUID.randomUUID()); //TODO - 리팩토링 필요
		Bookmark bookmark = bookmarkRepository.save(Bookmark.of(user, place));
		return bookmark.getId(); //TODO - 북마크 ID 리턴하는거 주안님이랑 상의
	}
}
