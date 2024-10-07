package com.flutter.alloffootball.admin.wrapper;

import com.flutter.alloffootball.admin.dto.ResponseSearchMatch;
import com.flutter.alloffootball.common.domain.match.Match;
import com.flutter.alloffootball.common.enums.MatchStatus;
import com.flutter.alloffootball.common.enums.SexType;
import org.springframework.stereotype.Component;

@Component
public class AdminMatchWrapper {

    public ResponseSearchMatch searchMatchWrap(Match match, long currentPerson) {
        return ResponseSearchMatch.builder()
            .matchId(match.getId())
            .matchDate(match.getMatchDate())
            .region(match.getField().getAddress().getRegion().getKo())
            .sex(SexType.getKo(match.getMatchSex()))
            .title(match.getField().getTitle())
            .person(String.format("%d/%d", currentPerson, match.getMaxPerson()))
            .matchStatus(MatchStatus.getKo(match.getMatchStatus()))
            .build();
    }
}
