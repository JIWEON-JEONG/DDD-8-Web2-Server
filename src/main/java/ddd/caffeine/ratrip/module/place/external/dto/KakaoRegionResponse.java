package ddd.caffeine.ratrip.module.place.external.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class KakaoRegionResponse {
	private KakaoRegionMeta meta;
	private List<KakaoRegionDocument> documents;
}
