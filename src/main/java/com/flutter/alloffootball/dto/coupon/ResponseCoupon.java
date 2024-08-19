package com.flutter.alloffootball.dto.coupon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class ResponseCoupon {

    private final long couponId;
    private final String title;
    private final int per;
    private final LocalDateTime expireDate;
}
