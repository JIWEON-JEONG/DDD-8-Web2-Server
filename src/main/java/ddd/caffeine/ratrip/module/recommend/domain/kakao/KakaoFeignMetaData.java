package ddd.caffeine.ratrip.module.recommend.domain.kakao;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoFeignMetaData {
	private boolean isEnd;
	private int pageableCount;
	private SameName sameName;
	private int totalCount;

	@NoArgsConstructor
	@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
	class SameName {
		private String keyword;
		private String[] region;
		private String selectedRegion;
	}
}
