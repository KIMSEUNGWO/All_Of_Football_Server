package com.flutter.alloffootball.dto.user;

import com.flutter.alloffootball.common.enums.Provider;
import com.flutter.alloffootball.common.enums.SexType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class ResponseUserProfile {

    private final long id;
    private final Provider provider;
    private final String image;
    private final String nickname;
    private final SexType sex;
    private final LocalDate birth;

    private final int couponCount;

    private final int cash;
}
