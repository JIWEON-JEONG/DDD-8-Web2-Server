package ddd.caffeine.ratrip.service.feign.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoFeignResponseDto {
	List<KakaoFeignModel> documents;
	KakaoFeignMetaData meta;
}
