package ddd.caffeine.ratrip.module.feign.place.domain.naver;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImageNaverModel {
	private List<ImageItem> items;
}
