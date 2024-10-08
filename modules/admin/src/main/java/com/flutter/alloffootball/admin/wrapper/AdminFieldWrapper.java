package com.flutter.alloffootball.admin.wrapper;

import com.flutter.alloffootball.admin.dto.field.ResponseFieldSimpInfo;
import com.flutter.alloffootball.admin.dto.field.ResponseSearchField;
import com.flutter.alloffootball.admin.dto.field.ResponseViewField;
import com.flutter.alloffootball.common.domain.BaseEntityImage;
import com.flutter.alloffootball.common.domain.field.Field;
import org.springframework.stereotype.Component;

@Component
public class AdminFieldWrapper {

    public ResponseSearchField searchFieldWrap(Field field) {
        return ResponseSearchField.builder()
            .fieldId(field.getId())
            .region(field.getAddress().getRegion().getKo())
            .title(field.getTitle())
            .address(field.getAddress().getAddress())
            .build();
    }

    public ResponseViewField viewFieldWrap(Field field) {
        return ResponseViewField.builder()
            .fieldId(field.getId())
            .images(field.getFieldImages().stream().map(BaseEntityImage::getStoreName).toList())
            .title(field.getTitle())
            .description(field.getDescription())
            .address(field.getAddress().getAddress())
            .link(field.getAddress().getLink())
            .region(field.getAddress().getRegion())
            .size(field.getFieldData().getSize())
            .parking(field.getFieldData().getParking())
            .shower(field.getFieldData().getShower())
            .toilet(field.getFieldData().getToilet())
            .build();
    }

    public ResponseFieldSimpInfo fieldSimpInfoWrap(Field field) {
        return ResponseFieldSimpInfo.builder()
            .fieldId(field.getId())
            .title(field.getTitle())
            .build();
    }
}
