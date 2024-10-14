package com.flutter.alloffootball.admin.dto.user;

import com.flutter.alloffootball.common.enums.Provider;
import com.flutter.alloffootball.common.enums.SexType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class ResponseViewUser {

    private final long userId;
    private final String profile;
    private final String nickname;
    private final LocalDate birth;
    private final SexType sex;
    private final Provider social;
    private final int cash;
    private final LocalDateTime createDate;
    private final String status;
}
