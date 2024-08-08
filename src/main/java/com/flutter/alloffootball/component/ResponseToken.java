package com.flutter.alloffootball.component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ResponseToken {

    private final String accessToken;
    private final String refreshToken;
}
