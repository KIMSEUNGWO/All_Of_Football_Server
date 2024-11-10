package com.flutter.alloffootball.admin.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class ResponseRegionStatistics {

    private final String region;
    private final int completedCount;
    private final int canceledCount;
}
