package com.flutter.alloffootball.wrapper;

import com.flutter.alloffootball.domain.BaseEntityImage;
import com.flutter.alloffootball.domain.field.Field;
import com.flutter.alloffootball.dto.field.ResponseFieldData;
import org.springframework.stereotype.Component;

@Component
public class FieldWrapper {

    public ResponseFieldData fieldDataWrap(Field field) {
        return ResponseFieldData.builder()
            .images(field.getFieldImages().stream().map(BaseEntityImage::getStoreName).toList())
            .address(field.getAddress())
            .fieldData(field.getFieldData())
            .build();
    }
}