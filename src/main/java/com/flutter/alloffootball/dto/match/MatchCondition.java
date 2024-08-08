package com.flutter.alloffootball.dto.match;

import com.flutter.alloffootball.enums.SexType;
import com.flutter.alloffootball.enums.region.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class MatchCondition {

    private final SexType sex;
    private final Region region;
    private final int limitPerson;
    private final int matchCount;
}
