package com.flutter.alloffootball.dto.match;

import com.flutter.alloffootball.common.enums.MatchStatus;
import com.flutter.alloffootball.common.enums.SexType;
import com.flutter.alloffootball.dto.field.ResponseFieldData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
public class ResponseMatchDetails {

    final Long matchId;
    final LocalDateTime matchDate;
    final SexType sexType;
    final int person;
    final int matchCount;
    final int matchHour;
    final MatchStatus matchStatus;
    private final ResponseFieldData field;

    // 로그인한 회원인 경우, 이미 참가했는지 여부
    private final boolean alreadyJoin;

    private final RequestMatchStatistics statistics;
}
