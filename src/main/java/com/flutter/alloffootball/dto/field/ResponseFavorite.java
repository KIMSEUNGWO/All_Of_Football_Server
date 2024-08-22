package com.flutter.alloffootball.dto.field;

import com.flutter.alloffootball.common.enums.region.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ResponseFavorite {

    private final long fieldId;
    private final String title;
    private final Region region;
    private final String address;
}
