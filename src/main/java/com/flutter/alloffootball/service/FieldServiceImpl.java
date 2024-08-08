package com.flutter.alloffootball.service;

import com.flutter.alloffootball.common.domain.field.Field;
import com.flutter.alloffootball.dto.field.ResponseFieldData;
import com.flutter.alloffootball.repository.FieldRepository;
import com.flutter.alloffootball.wrapper.FieldWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;

    private final FieldWrapper fieldWrapper;

    @Override
    public ResponseFieldData getFieldDetails(long fieldId) {
        Field field = fieldRepository.findById(fieldId);
        return fieldWrapper.fieldDataWrap(field);
    }
}
