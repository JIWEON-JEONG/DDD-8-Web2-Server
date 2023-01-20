package ddd.caffeine.ratrip.module.place.feign.naver.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeignBlogModel {
	private List<BlogItem> items;
}
