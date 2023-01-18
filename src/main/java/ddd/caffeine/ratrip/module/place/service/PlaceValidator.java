package ddd.caffeine.ratrip.module.place.service;

import static ddd.caffeine.ratrip.common.exception.ExceptionInformation.*;

import java.util.Optional;

import org.springframework.stereotype.Component;

import ddd.caffeine.ratrip.common.exception.domain.PlaceException;
import ddd.caffeine.ratrip.module.place.model.Place;

@Component
public class PlaceValidator {
	public Place validateExistPlace(Optional<Place> place) {
		return place.orElseThrow(() -> new PlaceException(NOT_FOUND_PLACE_EXCEPTION));
	}
}
