package com.flutter.alloffootball.common.exceptionhandler;

import com.flutter.alloffootball.common.dto.Response;
import com.flutter.alloffootball.common.exception.CustomRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomRuntimeException.class)
    public ResponseEntity<Response> handleCustomException(final CustomRuntimeException e) {
        log.error("CustomRuntimeException 발생 : " + e.getError());
        return ResponseEntity.badRequest().body(new Response(e.getError()));
    }
}
