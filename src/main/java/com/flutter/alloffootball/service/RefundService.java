package com.flutter.alloffootball.service;

import com.flutter.alloffootball.dto.order.RequestCancelOrder;

public interface RefundService {
    void cancelOrder(RequestCancelOrder cancelOrder, Long userId);
}
