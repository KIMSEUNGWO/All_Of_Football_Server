package com.flutter.alloffootball.admin.dto.field;

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
    private final String region;
    private final String title;
    private final String address;
}
