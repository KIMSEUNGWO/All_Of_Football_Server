package com.flutter.alloffootball.dto.field;

import com.flutter.alloffootball.common.domain.field.Field;
import com.flutter.alloffootball.common.enums.region.Region;
import lombok.Getter;

@Getter
public class ResponseFavorite {

    private final long fieldId;
    private final String title;
    private final Region region;
    private final String address;

    public ResponseFavorite(Field field) {
        this.fieldId = field.getId();
        this.title = field.getTitle();
        this.region = field.getAddress().getRegion();
        this.address = field.getAddress().getAddress();
    }
}
