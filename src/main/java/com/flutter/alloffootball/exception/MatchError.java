package com.flutter.alloffootball.exception;

public enum MatchError implements ErrorCode{

    MATCH_NOT_EXISTS;

    @Override
    public String getErrorCode() {
        return this.name();
    }
}
