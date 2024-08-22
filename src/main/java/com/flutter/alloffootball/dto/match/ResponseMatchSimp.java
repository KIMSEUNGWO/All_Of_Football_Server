package com.flutter.alloffootball.dto.match;

import com.flutter.alloffootball.common.enums.MatchStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@ToString
public class ResponseMatchSimp {

    private final Long matchId;
    private final MatchStatus matchStatus;
    private final LocalDateTime matchDate;
    private final MatchData matchData;
}
