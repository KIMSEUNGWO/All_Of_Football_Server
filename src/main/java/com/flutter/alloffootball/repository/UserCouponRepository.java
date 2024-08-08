package com.flutter.alloffootball.repository;

import com.flutter.alloffootball.domain.coupon.UserCoupon;
import com.flutter.alloffootball.domain.match.Match;
import com.flutter.alloffootball.dto.coupon.ResponseCouponUse;

import java.time.LocalDateTime;

public interface UserCouponRepository {
    UserCoupon findById(Long couponId);

    ResponseCouponUse useCoupon(UserCoupon userCoupon, long userId, LocalDateTime now, int totalPrice);
}
