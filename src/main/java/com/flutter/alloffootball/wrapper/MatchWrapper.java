package com.flutter.alloffootball.wrapper;

import com.flutter.alloffootball.domain.match.Match;
import com.flutter.alloffootball.domain.user.User;
import com.flutter.alloffootball.dto.match.MatchCondition;
import com.flutter.alloffootball.dto.match.ResponseMatchData;
import com.flutter.alloffootball.dto.match.ResponseMatchDetails;
import com.flutter.alloffootball.dto.match.ResponseMatchOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MatchWrapper {

    private final FieldWrapper fieldWrapper;

    public ResponseMatchData matchDataWrap(Match match) {
        return ResponseMatchData.builder()
            .status(match.getMatchStatus())
            .matchDate(match.getMatchDate())
            .title(match.getField().getTitle())
            .matchTime(match.getMatchTime())
            .matchCondition(matchConditionWrap(match))
            .build();
    }

    public MatchCondition matchConditionWrap(Match match) {
        return MatchCondition.builder()
            .sex(match.getMatchSex())
            .region(match.getField().getAddress().getRegion())
            .limitPerson(match.getMatchCount())
            .matchCount(match.getMatchCount())
            .build();
    }

    public ResponseMatchDetails matchDetailsWrap(Match match, boolean alreadyJoin) {
        return ResponseMatchDetails.builder()
            .matchData(matchDataWrap(match))
            .fieldData(fieldWrapper.fieldDataWrap(match.getField()))
            .alreadyJoin(alreadyJoin)
            .build();
    }

    public ResponseMatchOrder matchOrderWrap(Match match, User user) {
        return ResponseMatchOrder.builder()
            .title(match.getField().getTitle())
            .address(match.getField().getAddress())
            .matchDate(match.getMatchDate())
            .matchTime(match.getMatchTime())
            .hourPrice(match.getField().getFieldData().getHourPrice())
            .cash(user.getCash())
            .build();
    }
}