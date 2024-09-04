package com.flutter.alloffootball.common.jparepository;

import com.flutter.alloffootball.common.domain.match.Match;
import com.flutter.alloffootball.common.domain.orders.Order;
import com.flutter.alloffootball.common.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface JpaOrderRepository extends JpaRepository<Order, Long> {

    boolean existsByMatch_IdAndUser_IdAndOrderStatus(long matchId, long userId, OrderStatus status);
    List<Order> findAllByUser_IdAndOrderStatusAndMatch_MatchDateAfterOrderByMatch_MatchDate(Long userId, OrderStatus status, LocalDateTime now);

    Optional<Order> findByUser_IdAndMatch_Id(Long userId, Long matchId);
    long countByMatchAndOrderStatus(Match match, OrderStatus status);
}
