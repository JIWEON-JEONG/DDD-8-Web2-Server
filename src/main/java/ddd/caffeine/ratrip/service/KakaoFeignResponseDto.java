package ddd.caffeine.ratrip.service;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class KakaoFeignResponseDto {
    List<KakaoFeignModel> documents;
    KakaoFeignMetaData meta;
}
