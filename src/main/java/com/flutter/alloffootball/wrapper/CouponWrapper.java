package com.flutter.alloffootball.wrapper;

import com.flutter.alloffootball.common.domain.coupon.UserCoupon;
import com.flutter.alloffootball.dto.coupon.ResponseCoupon;
import org.springframework.stereotype.Component;


@Component
public class CouponWrapper {

    public ResponseCoupon couponWrap(UserCoupon userCoupon) {
        return ResponseCoupon.builder()
            .couponId(userCoupon.getId())
            .title(userCoupon.getCoupon().getTitle())
            .per(userCoupon.getCoupon().getDiscountPer())
            .expireDate(userCoupon.getExpireDate())
            .build();
    }
}
