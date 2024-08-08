package com.flutter.alloffootball.service;

import com.flutter.alloffootball.dto.order.RequestOrder;
import com.flutter.alloffootball.dto.order.ResponseOrderResult;

import java.time.LocalDateTime;

public interface OrderService {
    ResponseOrderResult order(RequestOrder requestOrder, long userId, LocalDateTime now);
}
