package com.flutter.alloffootball.admin.dto.field;

import com.flutter.alloffootball.common.enums.field.Parking;
import com.flutter.alloffootball.common.enums.field.Shower;
import com.flutter.alloffootball.common.enums.field.Toilet;
import com.flutter.alloffootball.common.enums.region.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class ResponseViewField {

    private final List<String> images;

    private final long fieldId;
    private final String title;
    private final Region region;
    private final String address;
    private final String link;

    private final int size;

    private final Parking parking;
    private final Toilet toilet;
    private final Shower shower;

    private final String description;
}
