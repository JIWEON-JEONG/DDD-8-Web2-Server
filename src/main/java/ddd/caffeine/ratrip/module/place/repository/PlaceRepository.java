package ddd.caffeine.ratrip.module.place.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ddd.caffeine.ratrip.module.place.model.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, UUID>, PlaceQueryRepository {

}
