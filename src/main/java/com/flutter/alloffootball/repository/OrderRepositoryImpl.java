package com.flutter.alloffootball.repository;

import com.flutter.alloffootball.config.security.CustomUserDetails;
import com.flutter.alloffootball.domain.match.Match;
import com.flutter.alloffootball.domain.orders.Order;
import com.flutter.alloffootball.domain.user.User;
import com.flutter.alloffootball.enums.MatchStatus;
import com.flutter.alloffootball.exception.OrderError;
import com.flutter.alloffootball.exception.OrderException;
import com.flutter.alloffootball.jparepository.JpaOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final MatchRepository matchRepository;
    private final JpaOrderRepository jpaOrderRepository;

    @Override
    public boolean existsByMatch_IdAndUser_Id(long matchId, CustomUserDetails userDetails) {
        if (userDetails == null) return false;
        return jpaOrderRepository.existsByMatch_IdAndUser_Id(matchId, userDetails.getUser().getId());
    }

    @Override
    public void valid(Match match, User user, int price) {

        if (match.getMatchSex() != null && match.getMatchSex() != user.getUserInfo().getSex()) {
            throw new OrderException(OrderError.NOT_MATCHED_SEX);
        }

        // 이미 참가 중인 유저인 경우
        boolean alreadyJoin = jpaOrderRepository.existsByMatch_IdAndUser_Id(match.getId(), user.getId());
        if (alreadyJoin) throw new OrderException(OrderError.ALREADY_JOIN);

        // 현재 보유한 캐시보다 적은 경우
        if (user.getCash() < price) throw new OrderException(OrderError.NOT_ENOUGH_CASH);

        // 이미 마감한 경우
        if (match.getMatchStatus() == MatchStatus.CLOSED) throw new OrderException(OrderError.ALREADY_CLOSED);

    }

    @Override
    public void save(Order saveOrder) {
        jpaOrderRepository.save(saveOrder);
    }
}
