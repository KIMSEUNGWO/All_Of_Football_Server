package com.flutter.alloffootball.dto.field;

import com.flutter.alloffootball.domain.field.Address;
import com.flutter.alloffootball.domain.field.FieldData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class ResponseFieldData {

    private final List<String> images;
    private final Address address;
    private final FieldData fieldData;
}
