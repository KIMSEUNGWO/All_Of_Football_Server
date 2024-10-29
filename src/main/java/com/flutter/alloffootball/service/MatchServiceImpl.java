package com.flutter.alloffootball.service;

import com.flutter.alloffootball.common.config.security.CustomUserDetails;
import com.flutter.alloffootball.common.domain.match.Match;
import com.flutter.alloffootball.common.domain.user.User;
import com.flutter.alloffootball.component.MatchStatisticsBuilder;
import com.flutter.alloffootball.dto.match.*;
import com.flutter.alloffootball.dto.order.ResponseOrderSimp;
import com.flutter.alloffootball.repository.MatchRepository;
import com.flutter.alloffootball.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final OrderRepository orderRepository;

    @Override
    public List<ResponseMatchView> search(RequestSearchMatch searchMatch, Pageable pageable) {
        return matchRepository.findAllByMatchData(searchMatch, pageable).stream()
            .map(ResponseMatchView::new)
            .toList();

    }

    // TODO MatchData 확인해야되는 Service
    @Override
    public List<ResponseMatchSimp> findAllByFieldIdToMatchData(long fieldId, Pageable pageable) {
        return matchRepository.findAllByFieldIdToMatchData(fieldId, pageable).stream()
            .map(ResponseMatchSimp::new)
            .toList();
    }

    @Override
    public ResponseMatchDetails getMatchDetails(long matchId, CustomUserDetails userDetails) {
        Match match = matchRepository.findById(matchId);
        boolean alreadyMatchJoin = orderRepository.isAlreadyJoin(matchId, userDetails);
        RequestMatchStatistics statistics = null;
        if (alreadyMatchJoin) {
            Stream<User> participants = orderRepository.getParticipants(match);
            statistics = MatchStatisticsBuilder.build(participants);
        }
        return new ResponseMatchDetails(match, alreadyMatchJoin, statistics);
    }

    @Override
    public ResponseOrderSimp getOrderSimp(long matchId, CustomUserDetails userDetails) {
        Match match = matchRepository.findById(matchId);
        return new ResponseOrderSimp(match);
    }

}
