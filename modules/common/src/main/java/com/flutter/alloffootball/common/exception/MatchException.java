package com.flutter.alloffootball.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MatchException extends RuntimeException {

    private final MatchError error;
}
