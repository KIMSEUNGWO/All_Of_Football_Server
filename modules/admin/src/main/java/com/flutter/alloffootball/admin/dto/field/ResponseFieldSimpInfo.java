package com.flutter.alloffootball.admin.dto.field;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseFieldSimpInfo {

    private final long fieldId;
    private final String title;
}
