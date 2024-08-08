package com.flutter.alloffootball.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenException extends RuntimeException {

    private final TokenError error;

}
