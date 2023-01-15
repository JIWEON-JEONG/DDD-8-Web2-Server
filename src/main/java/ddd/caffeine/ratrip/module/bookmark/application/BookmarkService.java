package ddd.caffeine.ratrip.module.bookmark.application;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ddd.caffeine.ratrip.module.bookmark.domain.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {
	private final BookmarkRepository bookmarkRepository;
	private final BookmarkValidator bookmarkValidator;

	public UUID addBookmark(final UUID placeId) {

		return UUID.randomUUID();
	}
}
