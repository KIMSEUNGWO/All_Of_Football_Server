package com.flutter.alloffootball.service;

import com.flutter.alloffootball.config.security.CustomUserDetails;
import com.flutter.alloffootball.dto.match.RequestSearchMatch;
import com.flutter.alloffootball.dto.match.ResponseMatchData;
import com.flutter.alloffootball.dto.match.ResponseMatchDetails;
import com.flutter.alloffootball.dto.match.ResponseMatchOrder;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MatchService {
    List<ResponseMatchData> findAllByMatchData(RequestSearchMatch searchMatch, Pageable pageable);

    List<ResponseMatchData> findAllByFieldIdToMatchData(long fieldId, Pageable pageable);

    ResponseMatchDetails getMatchDetails(long matchId, CustomUserDetails userDetails);

    ResponseMatchOrder getMatchOrder(long matchId, CustomUserDetails userDetails);
}
