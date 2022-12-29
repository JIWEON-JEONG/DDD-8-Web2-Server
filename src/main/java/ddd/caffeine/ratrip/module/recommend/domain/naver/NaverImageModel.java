package ddd.caffeine.ratrip.module.recommend.domain.naver;

import java.util.List;

import ddd.caffeine.ratrip.module.recommend.domain.naver.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NaverImageModel {
	private List<Item> items;
}
