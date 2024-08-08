package com.flutter.alloffootball.wrapper;

import com.flutter.alloffootball.domain.match.Match;
import com.flutter.alloffootball.domain.orders.Order;
import com.flutter.alloffootball.domain.user.User;
import com.flutter.alloffootball.dto.coupon.ResponseCouponUse;
import com.flutter.alloffootball.dto.order.ResponseOrderResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderWrapper {

    public ResponseOrderResult orderResultWrap(Match match, Order order, User user, ResponseCouponUse couponUse) {
        return ResponseOrderResult.builder()
            .totalPrice(match.getTotalPrice())
            .couponUse(couponUse)
            .orderPrice(order.getPrice())
            .remainCash(user.getCash())
            .build();
    }
}
