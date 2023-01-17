package ddd.caffeine.ratrip.module.bookmark.presentation.dto.response;

import java.util.UUID;

import com.querydsl.core.annotations.QueryProjection;

import ddd.caffeine.ratrip.module.place.model.Category;
import lombok.Getter;

@Getter
public class BookmarkPlaceDto {
	private final UUID id;
	private final String name;
	private final String detailAddress;
	private final String imageUrl;
	private final Category category;

	@QueryProjection
	public BookmarkPlaceDto(UUID id, String name, String detailAddress, String imageUrl, Category category) {
		this.id = id;
		this.name = name;
		this.detailAddress = detailAddress;
		this.imageUrl = imageUrl;
		this.category = category;
	}
}
