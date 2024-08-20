package com.flutter.alloffootball.dto.user;

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
    private final String image;
    private final String nickname;
    private final SexType sex;
    private final LocalDate birth;

    private final int favoriteCount;
    private final int couponCount;

    private final int cash;
}
