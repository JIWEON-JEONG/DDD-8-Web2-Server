package ddd.caffeine.ratrip.service.domain;

/**
 * SUPER_MARKET - 대형마트
 * RESTAURANT - 음식점
 * ACCOMMODATION - 숙박시설
 * ATTRACTION - 관광명소
 * CAFE - 카페
 */
public enum PlaceCategory {
    SUPER_MARKET("MT1"),
    RESTAURANT("FD6"),
    ACCOMMODATION("AD5"),
    ATTRACTION("AT4"),
    CAFE("CE7"),
    ;

    private String code;

    PlaceCategory(String code) {
        this.code = code;
    }
}
