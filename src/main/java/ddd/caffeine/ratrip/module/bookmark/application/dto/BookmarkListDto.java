package ddd.caffeine.ratrip.module.bookmark.application.dto;

import java.util.Set;

import ddd.caffeine.ratrip.module.place.model.Category;
import ddd.caffeine.ratrip.module.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class BookmarkListDto {
	private Set<Category> categories;
	private User user;

	public static BookmarkListDto of(Set<Category> categories, User user) {
		return new BookmarkListDto(categories, user);
	}
}
