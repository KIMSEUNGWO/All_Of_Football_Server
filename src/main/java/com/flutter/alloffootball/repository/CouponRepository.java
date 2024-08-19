package com.flutter.alloffootball.repository;

import com.flutter.alloffootball.common.domain.coupon.UserCoupon;

import java.util.List;

public interface CouponRepository {
    List<UserCoupon> findAllByNotUseCoupon(Long userId);
}
