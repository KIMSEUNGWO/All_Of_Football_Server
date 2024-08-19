package com.flutter.alloffootball.querydsl;

import java.time.LocalDateTime;
import java.util.List;

public interface QueryDslOrderRepository {

    List<Integer> findDistinctDaysWithDataInMonth(long userId, LocalDateTime startDate, LocalDateTime endDate);
}
