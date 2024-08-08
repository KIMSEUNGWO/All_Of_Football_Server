package com.flutter.alloffootball.service;

import com.flutter.alloffootball.domain.coupon.UserCoupon;
import com.flutter.alloffootball.domain.match.Match;
import com.flutter.alloffootball.domain.orders.Order;
import com.flutter.alloffootball.domain.user.User;
import com.flutter.alloffootball.dto.coupon.ResponseCouponUse;
import com.flutter.alloffootball.dto.order.RequestOrder;
import com.flutter.alloffootball.dto.order.ResponseOrderResult;
import com.flutter.alloffootball.enums.OrderStatus;
import com.flutter.alloffootball.repository.*;
import com.flutter.alloffootball.wrapper.OrderWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final MatchRepository matchRepository;
    private final OrderRepository orderRepository;
    private final CashRepository cashRepository;
    private final UserCouponRepository userCouponRepository;

    private final OrderWrapper orderWrapper;

    @Override
    public synchronized ResponseOrderResult order(RequestOrder requestOrder, long userId, LocalDateTime now) {
        Match match = matchRepository.findById(requestOrder.getMatchId());
        User user = userRepository.findById(userId);

        // 쿠폰사용을 하지 않았다면 userCoupon == null
        int totalPrice = match.getTotalPrice();

        UserCoupon userCoupon = userCouponRepository.findById(requestOrder.getCouponId());
        ResponseCouponUse couponUse = userCouponRepository.useCoupon(userCoupon, userId, now, totalPrice);

        // couponUse의 discount는 -로 되어있다.
        int finalPrice = totalPrice + (couponUse == null ? 0 : couponUse.getDiscount());

        orderRepository.valid(match, user, finalPrice);
        Order saveOrder = Order.builder()
            .match(match)
            .user(user)
            .price(finalPrice)
            .userCoupon(userCoupon)
            .orderStatus(OrderStatus.USE)
            .build();

        orderRepository.save(saveOrder);
        match.addOrder(saveOrder);

        cashRepository.use(user, saveOrder);

        return orderWrapper.orderResultWrap(match, saveOrder, user, couponUse);
    }
}
