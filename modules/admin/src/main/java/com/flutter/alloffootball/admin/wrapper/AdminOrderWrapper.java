package com.flutter.alloffootball.admin.wrapper;

import com.flutter.alloffootball.admin.dto.user.ResponseUserOrder;
import com.flutter.alloffootball.common.domain.orders.Order;
import org.springframework.stereotype.Component;

@Component
public class AdminOrderWrapper {

    public ResponseUserOrder userOrderWrap(Order order) {
        return ResponseUserOrder.builder()
            .orderId(order.getId())
            .title(order.getMatch().getField().getTitle())
            .matchId(order.getMatch().getId())
            .price(order.getPrice())
            .cancelDate(order.getCancelDate())
            .createDate(order.getCreateDate())
            .build();
    }
}
