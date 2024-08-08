package com.flutter.alloffootball.common.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SexType {

    MALE,
    FEMALE;

    @JsonCreator
    public static SexType fromJson(String value) {
        return SexType.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return name();
    }
}
