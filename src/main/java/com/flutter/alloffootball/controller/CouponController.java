package com.flutter.alloffootball.controller;

import com.flutter.alloffootball.common.config.security.CustomUserDetails;
import com.flutter.alloffootball.common.dto.Response;
import com.flutter.alloffootball.dto.coupon.ResponseCoupon;
import com.flutter.alloffootball.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @GetMapping("/user/coupons")
    public ResponseEntity<Response> coupons(@AuthenticationPrincipal CustomUserDetails userDetails) {
        List<ResponseCoupon> coupons = couponService.findAllByCouponsOnlyNotUse(userDetails.getUser().getId());
        return Response.ok(coupons);
    }
}
