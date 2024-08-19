package com.flutter.alloffootball.dto.match;

import com.flutter.alloffootball.common.enums.SexType;
import com.flutter.alloffootball.common.enums.region.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class MatchData {

    private final SexType sex;
    private final Region region;
    private final int limitPerson;
    private final int matchCount;
}
