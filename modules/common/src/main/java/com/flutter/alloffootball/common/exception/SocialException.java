package com.flutter.alloffootball.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SocialException extends RuntimeException {

    private final SocialError error;

}
