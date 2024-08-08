package com.flutter.alloffootball.common.jparepository;

import com.flutter.alloffootball.common.domain.orders.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<Order, Long> {

    boolean existsByMatch_IdAndUser_Id(long matchId, long userId);
}
