package com.flutter.alloffootball.dto.match;

import com.flutter.alloffootball.common.enums.MatchStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class ResponseMatchView {

    private final Long matchId;
    private final MatchStatus matchStatus;
    private final String title;
    private final LocalDateTime matchDate;
    private final MatchData matchData;
}
