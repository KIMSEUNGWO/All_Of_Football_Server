package com.flutter.alloffootball.common.enums.field;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Toilet {

    Y,
    N;

    @JsonCreator
    public static Toilet fromJson(String value) {
        return Toilet.valueOf(value.toUpperCase());
    }
}
