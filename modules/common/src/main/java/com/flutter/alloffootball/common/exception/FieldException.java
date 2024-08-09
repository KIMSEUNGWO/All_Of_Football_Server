package com.flutter.alloffootball.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FieldException extends RuntimeException {

    private final FieldError error;

}