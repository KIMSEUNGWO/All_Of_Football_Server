package com.flutter.alloffootball.jparepository;

import com.flutter.alloffootball.domain.coupon.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserCouponRepository extends JpaRepository<UserCoupon, Long> {
}
