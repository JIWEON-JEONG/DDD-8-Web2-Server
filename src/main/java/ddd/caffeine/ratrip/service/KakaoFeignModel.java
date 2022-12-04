package ddd.caffeine.ratrip.service;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoFeignModel {
	private String category_group_name;
	private String category_name;
	private String phone;
	private String place_name;
	private String place_url;
	private String road_address_name;
	private String x;
	private String y;
}
