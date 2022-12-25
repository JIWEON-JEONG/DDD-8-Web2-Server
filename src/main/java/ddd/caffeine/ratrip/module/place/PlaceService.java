package ddd.caffeine.ratrip.module.place;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * 1. 장소를 검색 하여, 있다면 데이터 내려주고, 없다면 api 호출.
 *
 * 예외
 * - 장소가 업데이트 되었을 경우. (이사 갔거나 뿌셨거나)
 * - 장소가 업데이트 되었는데 사진이 없을경우 -> 프론트에 기본적으로 사진 못찾음 이미지 띄워주게 해야 할 듯.
 *
 */
@Service
@RequiredArgsConstructor
public class PlaceService {

}
