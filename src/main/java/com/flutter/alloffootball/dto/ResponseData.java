package com.flutter.alloffootball.dto;

import com.flutter.alloffootball.exception.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseData<T> extends Response {

    private T data;

    public ResponseData(ErrorCode errorCode, T data) {
        super(errorCode);
        this.data = data;
    }
}
