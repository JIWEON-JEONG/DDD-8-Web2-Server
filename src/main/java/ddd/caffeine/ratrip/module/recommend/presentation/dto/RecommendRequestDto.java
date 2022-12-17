package ddd.caffeine.ratrip.module.recommend.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecommendRequestDto {

	private String region;
	private String keyword;
	private int page;
}
