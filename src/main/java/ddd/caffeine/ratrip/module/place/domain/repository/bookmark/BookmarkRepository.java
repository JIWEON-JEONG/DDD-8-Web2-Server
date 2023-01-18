package ddd.caffeine.ratrip.module.place.domain.repository.bookmark;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ddd.caffeine.ratrip.module.bookmark.domain.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, UUID>, BookmarkQueryRepository {
}
