package ddd.caffeine.ratrip.module.place.presentation.dto.bookmark;

import java.util.UUID;

import ddd.caffeine.ratrip.module.place.domain.sub_domain.Category;
import ddd.caffeine.ratrip.module.place.domain.repository.bookmark.dao.BookMarkPlaceDao;
import lombok.Getter;

@Getter
public class BookmarkPlaceModel {
	private UUID id;
	private String name;
	private String detailAddress;
	private String imageUrl;
	private Category category;
	private BookmarkResponseDto bookmark;

	public BookmarkPlaceModel(BookMarkPlaceDao dao) {
		this.id = dao.getId();
		this.name = dao.getName();
		this.detailAddress = dao.getDetailAddress();
		this.imageUrl = dao.getImageUrl();
		this.category = dao.getCategory();
		this.bookmark = new BookmarkResponseDto(dao.getBookmarkId(), dao.isActivated());
	}
}
