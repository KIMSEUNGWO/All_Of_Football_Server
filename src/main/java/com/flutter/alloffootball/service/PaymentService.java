package com.flutter.alloffootball.service;

import com.flutter.alloffootball.common.domain.user.User;
import com.flutter.alloffootball.common.enums.CashType;

public interface PaymentService {
    void receipt(Long userId, String message, CashType cashType, int amount);
    void receipt(User user, String message, CashType cashType, int amount);
}
