package com.flutter.alloffootball.common.enums.field;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Shower {

    Y,
    N;

    @JsonCreator
    public static Shower fromJson(String value) {
        return Shower.valueOf(value.toUpperCase());
    }
}
