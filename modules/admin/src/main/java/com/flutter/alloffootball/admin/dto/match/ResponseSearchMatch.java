package com.flutter.alloffootball.admin.dto.match;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class ResponseSearchMatch {

    private final Long matchId;
    private final LocalDateTime matchDate;
    private final String region;
    private final String sex;
    private final String title;
    private final String person;
    private final String matchStatus;
}