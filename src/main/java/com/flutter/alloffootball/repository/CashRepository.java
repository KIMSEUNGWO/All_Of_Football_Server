package com.flutter.alloffootball.repository;

import com.flutter.alloffootball.common.domain.orders.Order;
import com.flutter.alloffootball.common.domain.user.User;

public interface CashRepository {

    void use(User user, Order saveOrder);
}
