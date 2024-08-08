package com.flutter.alloffootball.service;

import com.flutter.alloffootball.dto.field.ResponseFieldData;

public interface FieldService {
    ResponseFieldData getFieldDetails(long fieldId);
}
