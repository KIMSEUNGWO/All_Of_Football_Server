package com.flutter.alloffootball.dto.match;

import com.flutter.alloffootball.dto.field.ResponseFieldData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ResponseMatchDetails {

    private final ResponseFieldData fieldData;
    private final ResponseMatchData matchData;

    // 로그인한 회원인 경우, 이미 참가했는지 여부
    private final boolean alreadyJoin;
}
