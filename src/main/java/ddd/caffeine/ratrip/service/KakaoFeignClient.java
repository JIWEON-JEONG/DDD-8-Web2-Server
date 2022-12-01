package ddd.caffeine.ratrip.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(url = "https://dapi.kakao.com/v2/local")
public interface KakaoFeignClient extends FeignClient {
    @RequestMapping("")

}
