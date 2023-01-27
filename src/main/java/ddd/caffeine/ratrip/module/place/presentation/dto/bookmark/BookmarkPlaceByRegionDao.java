package ddd.caffeine.ratrip.module.place.presentation.dto.bookmark;

import java.util.UUID;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;

@Getter
public class BookmarkPlaceByRegionDao {
	private final UUID id;
	private final String name;
	private final String imageLink;

	@QueryProjection
	public BookmarkPlaceByRegionDao(UUID id, String name, String imageLink) {
		this.id = id;
		this.name = name;
		this.imageLink = imageLink;
	}
}
