package com.flutter.alloffootball.common.jparepository;

import com.flutter.alloffootball.common.domain.field.FieldImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFieldImageRepository extends JpaRepository<FieldImage, Long> {
}
