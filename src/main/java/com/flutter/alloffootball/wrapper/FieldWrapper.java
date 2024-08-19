package com.flutter.alloffootball.wrapper;

import com.flutter.alloffootball.common.domain.BaseEntityImage;
import com.flutter.alloffootball.common.domain.field.Field;
import com.flutter.alloffootball.dto.field.ResponseFieldData;
import org.springframework.stereotype.Component;

@Component
public class FieldWrapper {

    public ResponseFieldData fieldDataWrap(Field field, boolean favorite) {
        return ResponseFieldData.builder()
            .fieldId(field.getId())
            .title(field.getTitle())
            .address(field.getAddress())
            .fieldData(field.getFieldData())
            .description(field.getDescription())
            .favorite(favorite)
            .images(field.getFieldImages().stream().map(BaseEntityImage::getStoreName).toList())
            .build();
    }
}