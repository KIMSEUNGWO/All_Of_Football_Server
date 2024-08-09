package com.flutter.alloffootball.common.enums.region;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.flutter.alloffootball.common.enums.region.ParentRegion.*;


@Getter
@AllArgsConstructor
public enum Region {

    // 도쿄도
    ADACHI(TOKYO),
    ARAKAWA(TOKYO),
    ITABASHI(TOKYO),
    EDOGAWA(TOKYO),
    OTA(TOKYO),
    KATSUSHIKA(TOKYO),
    KITA(TOKYO),
    KOTO(TOKYO),
    SHINAGAWA(TOKYO),
    SHIBUYA(TOKYO),
    SHINJUKU(TOKYO),
    SUGINAMI(TOKYO),
    SUMIDA(TOKYO),
    SETAGAYA(TOKYO),
    TAITO(TOKYO),
    CHUO(TOKYO),
    CHIYODA(TOKYO),
    TOSHIMA(TOKYO),
    NAKANO(TOKYO),
    NERIMA(TOKYO),
    BUNKYO(TOKYO),
    MINATO(TOKYO),
    MEGURO(TOKYO);


    private final ParentRegion parentRegion;

    @JsonCreator
    public static Region fromJson(String data) {
        for (Region region : Region.values()) {
            if (region.name().equals(data)) return region;
        }
        return null;
    }

    @JsonValue
    public String toJson() {
        return this.name();
    }

}
