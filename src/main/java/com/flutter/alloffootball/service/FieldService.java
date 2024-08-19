package com.flutter.alloffootball.service;

import com.flutter.alloffootball.common.config.security.CustomUserDetails;
import com.flutter.alloffootball.dto.field.ResponseFieldData;

public interface FieldService {
    ResponseFieldData getFieldDetails(long fieldId, CustomUserDetails userDetails);
}
