package com.flutter.alloffootball.service;

import com.flutter.alloffootball.common.config.security.CustomUserDetails;
import com.flutter.alloffootball.common.domain.Favorite;
import com.flutter.alloffootball.common.domain.field.Field;
import com.flutter.alloffootball.common.jparepository.JpaFavoriteRepository;
import com.flutter.alloffootball.dto.field.ResponseFavorite;
import com.flutter.alloffootball.dto.field.ResponseFieldData;
import com.flutter.alloffootball.repository.FieldRepository;
import com.flutter.alloffootball.wrapper.FieldWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;
    private final JpaFavoriteRepository jpaFavoriteRepository;
    private final FieldWrapper fieldWrapper;

    @Override
    public ResponseFieldData getFieldDetails(long fieldId, CustomUserDetails userDetails) {
        Field field = fieldRepository.findById(fieldId);
        return fieldWrapper.fieldDataWrap(field);
    }

    @Override
    public List<ResponseFavorite> findAllByFavorite(Long userId) {
        return jpaFavoriteRepository.findAllByUser_Id(userId)
            .stream()
            .map(favorite -> fieldWrapper.fieldFavoriteWrap(favorite.getField()))
            .toList();
    }
}
