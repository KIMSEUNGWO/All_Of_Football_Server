package com.flutter.alloffootball.dto.match;

import com.flutter.alloffootball.common.enums.MatchStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class ResponseMatchData {

    private final MatchStatus status;
    private final LocalDateTime matchDate;

    private final String title;
    private final int matchTime;
    private final MatchCondition matchCondition;

}
