package com.flutter.alloffootball.querydsl;

import com.flutter.alloffootball.common.domain.orders.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface QueryDslOrderRepository {

    List<Order> findDistinctDaysWithDataInMonth(long userId, LocalDateTime startDate, LocalDateTime endDate);
}
