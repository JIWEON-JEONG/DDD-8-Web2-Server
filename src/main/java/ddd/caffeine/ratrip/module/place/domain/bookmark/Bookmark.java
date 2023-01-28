package ddd.caffeine.ratrip.module.place.domain.bookmark;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import ddd.caffeine.ratrip.common.jpa.AuditingTimeEntity;
import ddd.caffeine.ratrip.module.place.domain.Place;
import ddd.caffeine.ratrip.module.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@IdClass(BookmarkId.class) //
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark extends AuditingTimeEntity {
	@EmbeddedId
	private BookmarkId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "place_id", columnDefinition = "BINARY(16)", nullable = false)
	private Place place;

	@NotNull
	@Column(name = "is_activated", columnDefinition = "TINYINT(1)")
	private boolean isActivated = Boolean.TRUE;

	@Builder
	private Bookmark(BookmarkId id, User user, Place place) {
		this.id = id;
		this.user = user;
		this.place = place;
	}

	public static Bookmark of(BookmarkId id, User user, Place place) {
		return Bookmark.builder()
			.id(id)
			.user(user)
			.place(place)
			.build();
	}

	public void changeBookmarkState() {
		if (this.isActivated) {
			this.isActivated = Boolean.FALSE;
			return;
		}
		this.isActivated = Boolean.TRUE;
	}
}
