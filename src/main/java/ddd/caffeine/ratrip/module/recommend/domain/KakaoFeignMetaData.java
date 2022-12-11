package ddd.caffeine.ratrip.module.recommend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoFeignMetaData {
	private boolean is_end;
	private int pageable_count;
	private SameName same_name;
	private int total_count;

	@NoArgsConstructor
	class SameName {
		private String keyword;
		private String[] region;
		private String selected_region;
	}
}
