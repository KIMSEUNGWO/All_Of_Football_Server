package com.flutter.alloffootball.querydsl;

import com.flutter.alloffootball.common.domain.match.QMatch;
import com.flutter.alloffootball.common.domain.match.QMatches;
import com.flutter.alloffootball.common.domain.orders.Order;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.flutter.alloffootball.common.domain.orders.QOrder.*;

@Repository
@RequiredArgsConstructor
public class QueryDslOrderRepositoryImpl implements QueryDslOrderRepository {

    private final JPAQueryFactory query;

    @Override
    public List<Order> findDistinctDaysWithDataInMonth(long userId, LocalDateTime startDate, LocalDateTime endDate) {
        return query.selectDistinct(order)
            .from(order)
            .join(order.match, QMatch.match)
            .where(order.user.id.eq(userId).and(order.match.matchDate.between(startDate, endDate)))
            .fetch();
    }
}
