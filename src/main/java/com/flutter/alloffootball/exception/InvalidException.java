package com.flutter.alloffootball.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InvalidException extends RuntimeException {

    private final DefaultError error;
}
