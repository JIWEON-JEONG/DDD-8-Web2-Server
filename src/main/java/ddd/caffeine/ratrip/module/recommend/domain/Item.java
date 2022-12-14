package ddd.caffeine.ratrip.module.recommend.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.LowerCaseStrategy.class)
public class Item {
	private String link;
	private String thumbnail;
	private String sizeHeight;
	private String sizeWidth;
}
