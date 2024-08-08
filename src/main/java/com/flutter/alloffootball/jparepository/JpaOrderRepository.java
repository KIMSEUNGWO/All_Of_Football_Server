package com.flutter.alloffootball.jparepository;

import com.flutter.alloffootball.domain.orders.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<Order, Long> {

    boolean existsByMatch_IdAndUser_Id(long matchId, long userId);
}
