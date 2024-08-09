package com.flutter.alloffootball.admin.wrapper;

import com.flutter.alloffootball.admin.dto.ResponseSearchField;
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
}
