package com.flutter.alloffootball.repository;

import com.flutter.alloffootball.common.domain.Cash;
import com.flutter.alloffootball.common.domain.orders.Order;
import com.flutter.alloffootball.common.domain.user.User;

import java.util.List;

public interface CashRepository {

    void use(User user, Order saveOrder);

    List<Cash> findAllByReceipt(Long userId);
}
