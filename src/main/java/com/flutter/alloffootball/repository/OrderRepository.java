package com.flutter.alloffootball.repository;

import com.flutter.alloffootball.common.config.security.CustomUserDetails;
import com.flutter.alloffootball.common.domain.match.Match;
import com.flutter.alloffootball.common.domain.orders.Order;
import com.flutter.alloffootball.common.domain.user.User;

public interface OrderRepository {
    boolean existsByMatch_IdAndUser_Id(long matchId, CustomUserDetails userDetails);

    void valid(Match match, User user, int price);

    void save(Order saveOrder);
}
