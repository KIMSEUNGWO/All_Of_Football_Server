package com.flutter.alloffootball.service;

import com.flutter.alloffootball.config.security.CustomUserDetails;
import com.flutter.alloffootball.domain.match.Match;
import com.flutter.alloffootball.dto.match.RequestSearchMatch;
import com.flutter.alloffootball.dto.match.ResponseMatchData;
import com.flutter.alloffootball.dto.match.ResponseMatchDetails;
import com.flutter.alloffootball.dto.match.ResponseMatchOrder;
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
    public List<ResponseMatchData> findAllByMatchData(RequestSearchMatch searchMatch, Pageable pageable) {
        return matchRepository.findAllByMatchData(searchMatch, pageable).stream()
            .map(matchWrapper::matchDataWrap)
            .toList();

    }

    @Override
    public List<ResponseMatchData> findAllByFieldIdToMatchData(long fieldId, Pageable pageable) {
        return matchRepository.findAllByFieldIdToMatchData(fieldId, pageable).stream()
            .map(matchWrapper::matchDataWrap)
            .toList();
    }

    @Override
    public ResponseMatchDetails getMatchDetails(long matchId, CustomUserDetails userDetails) {
        Match match = findById(matchId);
        boolean alreadyMatchJoin = orderRepository.existsByMatch_IdAndUser_Id(matchId, userDetails);
        return matchWrapper.matchDetailsWrap(match, alreadyMatchJoin);
    }

    @Override
    public ResponseMatchOrder getMatchOrder(long matchId, CustomUserDetails userDetails) {
        Match match = findById(matchId);
        return matchWrapper.matchOrderWrap(match, userDetails.getUser());
    }

    private Match findById(Long matchId) {
        return matchRepository.findById(matchId);
    }
}
