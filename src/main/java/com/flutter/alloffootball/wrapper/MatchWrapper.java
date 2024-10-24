package com.flutter.alloffootball.wrapper;

import com.flutter.alloffootball.common.domain.match.Match;
import com.flutter.alloffootball.common.domain.user.User;
import com.flutter.alloffootball.dto.match.*;
import com.flutter.alloffootball.dto.order.ResponseOrderSimp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MatchWrapper {

    private final FieldWrapper fieldWrapper;

    public ResponseMatchView matchViewWrap(Match match) {
        return ResponseMatchView.builder()
            .matchId(match.getId())
            .matchStatus(match.getMatchStatus())
            .matchDate(match.getMatchDate())
            .title(match.getField().getTitle())
            .matchData(matchConditionWrap(match))
            .build();
    }

    public MatchData matchConditionWrap(Match match) {
        return MatchData.builder()
            .sex(match.getMatchSex())
            .region(match.getField().getAddress().getRegion())
            .person(match.getPersonCount())
            .matchCount(match.getMatchCount())
            .build();
    }

    public ResponseMatchDetails matchDetailsWrap(Match match, boolean alreadyJoin, RequestMatchStatistics statistics) {
        return ResponseMatchDetails.builder()
            .matchId(match.getId())
            .matchDate(match.getMatchDate())
            .sexType(match.getMatchSex())
            .person(match.getPersonCount())
            .price(match.getPrice())
            .matchCount(match.getMatchCount())
            .matchHour(match.getMatchTime())
            .field(fieldWrapper.fieldDataWrap(match.getField()))
            .matchStatus(match.getMatchStatus())
            .alreadyJoin(alreadyJoin)
            .statistics(statistics)
            .build();
    }

    public ResponseMatchSimp matchSimpWrap(Match match) {
        return ResponseMatchSimp.builder()
            .matchId(match.getId())
            .matchStatus(match.getMatchStatus())
            .matchDate(match.getMatchDate())
            .matchData(MatchData.builder()
                .sex(match.getMatchSex())
                .person(match.getPersonCount())
                .matchCount(match.getMatchCount())
                .build())
            .build();
    }

    public ResponseOrderSimp orderSimpWrap(Match match, User user) {
        return ResponseOrderSimp.builder()
            .title(match.getField().getTitle())
            .totalPrice(match.getPrice())
            .matchHour(match.getMatchTime())
            .address(match.getField().getAddress())
            .matchDate(match.getMatchDate())
            .build();
    }
}