package ddd.caffeine.ratrip.module.place.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import ddd.caffeine.ratrip.common.model.Region;
import ddd.caffeine.ratrip.module.place.model.Place;

public interface PlaceQueryRepository {
	Slice<Place> findPlacesInRegions(List<Region> regions, Pageable pageable);
}
