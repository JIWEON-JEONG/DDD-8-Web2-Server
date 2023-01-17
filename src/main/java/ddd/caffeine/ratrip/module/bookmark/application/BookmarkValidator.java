package ddd.caffeine.ratrip.module.bookmark.application;

import static ddd.caffeine.ratrip.common.exception.ExceptionInformation.*;

import org.springframework.stereotype.Component;

import ddd.caffeine.ratrip.common.exception.domain.BookmarkException;
import ddd.caffeine.ratrip.module.bookmark.domain.Bookmark;

@Component
public class BookmarkValidator {

	public void validateExistBookmark(Bookmark bookmark) {
		if (bookmark != null) {
			throw new BookmarkException(ALREADY_EXIST_BOOKMARK_EXCEPTION);
		}
	}

}
