package com.flutter.alloffootball.jparepository;

import com.flutter.alloffootball.domain.coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCouponRepository extends JpaRepository<Coupon, Long> {
}
