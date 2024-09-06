package com.flutter.alloffootball.service;

import com.flutter.alloffootball.common.component.DateRangeUtil;
import com.flutter.alloffootball.common.domain.coupon.UserCoupon;
import com.flutter.alloffootball.common.domain.match.Match;
import com.flutter.alloffootball.common.domain.orders.Order;
import com.flutter.alloffootball.common.domain.user.User;
import com.flutter.alloffootball.common.enums.CashType;
import com.flutter.alloffootball.dto.coupon.ResponseCouponUse;
import com.flutter.alloffootball.dto.match.ResponseMatchView;
import com.flutter.alloffootball.dto.order.RequestOrder;
import com.flutter.alloffootball.dto.order.ResponseOrderResult;
import com.flutter.alloffootball.common.enums.OrderStatus;
import com.flutter.alloffootball.repository.*;
import com.flutter.alloffootball.wrapper.MatchWrapper;
import com.flutter.alloffootball.wrapper.OrderWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final PaymentRepository paymentRepository;

    private final UserRepository userRepository;
    private final MatchRepository matchRepository;
    private final OrderRepository orderRepository;
    private final UserCouponRepository userCouponRepository;

    private final OrderWrapper orderWrapper;
    private final MatchWrapper matchWrapper;

    @Override
    public synchronized ResponseOrderResult order(RequestOrder requestOrder, long userId, LocalDateTime now) {
        Match match = matchRepository.findById(requestOrder.getMatchId());
        User user = userRepository.findById(userId);
        UserCoupon userCoupon = userCouponRepository.findById(requestOrder.getCouponId());

        int price = match.getTotalPrice();

        // 쿠폰사용을 하지 않았다면 userCoupon == null
        ResponseCouponUse couponUse = userCouponRepository.useCoupon(userCoupon, userId, now, price);

        int finalPrice = (couponUse == null) ? price : couponUse.getTotalPrice();

        orderRepository.valid(match, user, finalPrice);
        Order saveOrder = Order.builder()
            .match(match)
            .user(user)
            .price(finalPrice)
            .userCoupon(userCoupon)
            .orderStatus(OrderStatus.USE)
            .build();

        orderRepository.save(saveOrder);

        orderRepository.refreshMatchStatus(match);

        paymentRepository.receipt(user, "경기 참여", CashType.USE, finalPrice);

        return orderWrapper.orderResultWrap(match, saveOrder, user, couponUse);
    }

    @Transactional(readOnly = true)
    @Override
    public Map<Integer, List<ResponseMatchView>> getHistory(LocalDateTime date, User user) {
        LocalDateTime startDate = DateRangeUtil.getStartOfMonth(date);
        LocalDateTime endDate = DateRangeUtil.getEndOfMonth(date);

        return orderRepository.getHistory(user.getId(), startDate, endDate)
            .stream()
            .map(order -> matchWrapper.matchViewWrap(order.getMatch()))
            .collect(Collectors.groupingBy(matchView -> matchView.getMatchDate().getDayOfMonth()));
    }

    @Override
    public List<ResponseMatchView> findAllByUserIdAndMatchDateAfter(Long userId, LocalDateTime now) {
        return orderRepository.findAllByMatchSoon(userId, now)
            .stream()
            .map(order -> matchWrapper.matchViewWrap(order.getMatch()))
            .toList();
    }

}
