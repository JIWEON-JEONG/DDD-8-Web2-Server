package ddd.caffeine.ratrip.module.place.domain.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import ddd.caffeine.ratrip.common.model.Region;
import ddd.caffeine.ratrip.module.place.domain.repository.dao.CategoryPlaceByRegionDao;
import ddd.caffeine.ratrip.module.place.domain.repository.dao.PlaceBookmarkDao;
import ddd.caffeine.ratrip.module.place.domain.repository.dao.PlaceDetailBookmarkDao;
import ddd.caffeine.ratrip.module.place.domain.sub_domain.Category;
import ddd.caffeine.ratrip.module.user.domain.User;

public interface PlaceQueryRepository {
	Slice<PlaceBookmarkDao> findPlacesInRegions(List<Region> regions, Pageable pageable);

	Slice<PlaceBookmarkDao> findPlacesInRegion(Region region, Pageable pageable);

	PlaceBookmarkDao findByThirdPartyID(String thirdPartyID);

	PlaceDetailBookmarkDao findByUUID(UUID id);

	Slice<CategoryPlaceByRegionDao> getCategoryPlacesByRegion(Region region, Category category,
		Pageable pageable);
}
