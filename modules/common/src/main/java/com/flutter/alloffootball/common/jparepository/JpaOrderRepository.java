package com.flutter.alloffootball.common.jparepository;

import com.flutter.alloffootball.common.domain.orders.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaOrderRepository extends JpaRepository<Order, Long> {

    boolean existsByMatch_IdAndUser_Id(long matchId, long userId);
    List<Order> findAllByUser_IdAndMatch_MatchDateBetween(long userId, LocalDateTime startDate, LocalDateTime endDate);
}
