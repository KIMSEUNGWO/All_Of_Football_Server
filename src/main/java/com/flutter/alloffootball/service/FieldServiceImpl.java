package com.flutter.alloffootball.service;

import com.flutter.alloffootball.common.config.security.CustomUserDetails;
import com.flutter.alloffootball.common.domain.field.Field;
import com.flutter.alloffootball.common.jparepository.JpaFavoriteRepository;
import com.flutter.alloffootball.dto.field.ResponseFieldData;
import com.flutter.alloffootball.repository.FieldRepository;
import com.flutter.alloffootball.wrapper.FieldWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;
    private final JpaFavoriteRepository jpaFavoriteRepository;
    private final FieldWrapper fieldWrapper;

    @Override
    public ResponseFieldData getFieldDetails(long fieldId, CustomUserDetails userDetails) {
        Field field = fieldRepository.findById(fieldId);
        boolean favorite = userDetails != null && jpaFavoriteRepository.existsByUser_IdAndField_Id(userDetails.getUser().getId(), fieldId);
        return fieldWrapper.fieldDataWrap(field, favorite);
    }
}
