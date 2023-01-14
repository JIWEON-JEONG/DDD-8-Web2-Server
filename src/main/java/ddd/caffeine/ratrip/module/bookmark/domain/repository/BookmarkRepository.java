package ddd.caffeine.ratrip.module.bookmark.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ddd.caffeine.ratrip.module.bookmark.domain.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, UUID> {
}
