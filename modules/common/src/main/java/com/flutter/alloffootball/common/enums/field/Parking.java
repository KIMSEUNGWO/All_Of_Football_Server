package com.flutter.alloffootball.common.enums.field;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Parking {

    FREE,
    PAID,
    NOT_AVAILABLE;


    @JsonCreator
    public static Parking fromJson(String value) {
        return Parking.valueOf(value.toUpperCase());
    }
}
