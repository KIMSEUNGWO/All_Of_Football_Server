package com.flutter.alloffootball.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomRuntimeException extends RuntimeException {

    private final ErrorCode error;
}
