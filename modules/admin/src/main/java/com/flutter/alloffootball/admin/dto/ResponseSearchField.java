package com.flutter.alloffootball.admin.dto;

import com.flutter.alloffootball.common.enums.region.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class ResponseSearchField {

    private final Long fieldId;
    private final Region region;
    private final String title;
    private final String address;
}
