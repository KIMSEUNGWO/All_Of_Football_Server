package com.flutter.alloffootball.service;

import com.flutter.alloffootball.common.domain.user.User;
import com.flutter.alloffootball.dto.match.ResponseMatchView;
import com.flutter.alloffootball.dto.order.RequestOrder;
import com.flutter.alloffootball.dto.order.ResponseOrderResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    ResponseOrderResult order(RequestOrder requestOrder, long userId, LocalDateTime now);

    List<ResponseMatchView> getHistory(LocalDateTime date, User user);

    List<Integer> getCalendar(LocalDateTime date, User user);
}
