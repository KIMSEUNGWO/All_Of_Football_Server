package com.flutter.alloffootball.service;

import com.flutter.alloffootball.common.config.security.CustomUserDetails;
import com.flutter.alloffootball.common.domain.match.Match;
import com.flutter.alloffootball.dto.match.*;
import com.flutter.alloffootball.dto.order.ResponseOrderSimp;
import com.flutter.alloffootball.repository.MatchRepository;
import com.flutter.alloffootball.repository.OrderRepository;
import com.flutter.alloffootball.wrapper.MatchWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final OrderRepository orderRepository;

    private final MatchWrapper matchWrapper;

    @Override
    public List<ResponseMatchView> findAllByMatchData(RequestSearchMatch searchMatch, Pageable pageable) {
        return matchRepository.findAllByMatchData(searchMatch, pageable).stream()
            .map(matchWrapper::matchViewWrap)
            .toList();

    }

    @Override
    public List<ResponseMatchSimp> findAllByFieldIdToMatchData(long fieldId, Pageable pageable) {
        return matchRepository.findAllByFieldIdToMatchData(fieldId, pageable).stream()
            .map(matchWrapper::matchSimpWrap)
            .toList();
    }

    @Override
    public ResponseMatchDetails getMatchDetails(long matchId, CustomUserDetails userDetails) {
        Match match = matchRepository.findById(matchId);
        boolean alreadyMatchJoin = orderRepository.existsByMatch_IdAndUser_Id(matchId, userDetails);
        return matchWrapper.matchDetailsWrap(match, alreadyMatchJoin);
    }

    @Override
    public ResponseOrderSimp getOrderSimp(long matchId, CustomUserDetails userDetails) {
        Match match = matchRepository.findById(matchId);
        return matchWrapper.orderSimpWrap(match, userDetails.getUser());
    }

}
