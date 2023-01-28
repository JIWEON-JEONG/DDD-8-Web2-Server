package ddd.caffeine.ratrip.module.place.domain.bookmark;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
public class BookmarkId implements Serializable {
	private UUID user;
	private UUID place;

	public BookmarkId(UUID userUUID, UUID placeUUID) {
		this.user = userUUID;
		this.place = placeUUID;
	}
}
