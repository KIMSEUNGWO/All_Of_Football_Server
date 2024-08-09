package com.flutter.alloffootball.admin.wrapper;

import com.flutter.alloffootball.admin.dto.ResponseSearchField;
import com.flutter.alloffootball.admin.dto.ResponseViewField;
import com.flutter.alloffootball.common.domain.BaseEntityImage;
import com.flutter.alloffootball.common.domain.field.Field;
import org.springframework.stereotype.Component;

@Component
public class AdminFieldWrapper {

    public ResponseSearchField searchFieldWrap(Field field) {
        return ResponseSearchField.builder()
            .fieldId(field.getId())
            .region(field.getAddress().getRegion())
            .title(field.getTitle())
            .address(field.getAddress().getAddress())
            .build();
    }

    public ResponseViewField viewFieldWrap(Field field) {
        return ResponseViewField.builder()
            .images(field.getFieldImages().stream().map(BaseEntityImage::getStoreName).toList())
            .title(field.getTitle())
            .description(field.getDescription())
            .address(field.getAddress().getAddress())
            .region(field.getAddress().getRegion())
            .sizeX(field.getFieldData().getSizeX())
            .sizeY(field.getFieldData().getSizeY())
            .parking(field.getFieldData().getParking())
            .shower(field.getFieldData().getShower())
            .toilet(field.getFieldData().getToilet())
            .hourPrice(field.getFieldData().getHourPrice())
            .build();
    }
}
