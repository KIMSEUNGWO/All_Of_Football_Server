package com.flutter.alloffootball.dto.field;

import com.flutter.alloffootball.common.domain.field.Address;
import com.flutter.alloffootball.common.domain.field.FieldData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class ResponseFieldData {

    private final Long fieldId;
    private final String title;
    private final Address address;
    private final FieldData fieldData;
    private final String description;
    private final List<String> images;
}
