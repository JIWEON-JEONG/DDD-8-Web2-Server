package ddd.caffeine.ratrip.module.place.presentation.dto.bookmark;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetNearbyBookmarkPlaceResponseDto {
	private final UUID id;
	private final String imageLink;
}
