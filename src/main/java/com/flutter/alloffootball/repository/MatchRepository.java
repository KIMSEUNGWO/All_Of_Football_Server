package com.flutter.alloffootball.repository;

import com.flutter.alloffootball.domain.match.Match;
import com.flutter.alloffootball.dto.match.RequestSearchMatch;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MatchRepository {
    List<Match> findAllByMatchData(RequestSearchMatch searchMatch, Pageable pageable);
    List<Match> findAllByFieldIdToMatchData(long fieldId, Pageable pageable);

    Match findById(Long matchId);
}
