package com.flutter.alloffootball.common.jparepository;

import com.flutter.alloffootball.common.domain.coupon.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserCouponRepository extends JpaRepository<UserCoupon, Long> {
}
