package com.flutter.alloffootball.repository;

import com.flutter.alloffootball.domain.field.Field;
import com.flutter.alloffootball.exception.FieldError;
import com.flutter.alloffootball.exception.FieldException;
import com.flutter.alloffootball.jparepository.JpaFieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FieldRepositoryImpl implements FieldRepository {

    private final JpaFieldRepository jpaFieldRepository;

    @Override
    public Field findById(long fieldId) {
        return jpaFieldRepository.findById(fieldId)
            .orElseThrow(() -> new FieldException(FieldError.FIELD_NOT_EXISTS));
    }

    @Override
    public void save(Field saveField) {
        jpaFieldRepository.save(saveField);
    }
}
