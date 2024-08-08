package com.flutter.alloffootball.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderException extends RuntimeException{

    private final OrderError error;
}
