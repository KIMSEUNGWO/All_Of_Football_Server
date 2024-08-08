package com.flutter.alloffootball.exception;

public enum OrderError implements ErrorCode {

    ORDER_NOT_EXISTS,
    NOT_ENOUGH_CASH,
    ALREADY_JOIN,
    ALREADY_CLOSED,
    NOT_MATCHED_SEX,
    ;

    @Override
    public String getErrorCode() {
        return this.name();
    }
}
