package ddd.caffeine.ratrip.service.feign.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Todo: 디자인 분들과 프론트 분들 필요한 정보 받아서 최적화 할 예정
 */
@Getter
@NoArgsConstructor
public class KakaoFeignResponseDto {
	List<KakaoFeignModel> documents;
	KakaoFeignMetaData meta;
}
