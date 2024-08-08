package com.flutter.alloffootball.jparepository;

import com.flutter.alloffootball.domain.field.FieldImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFieldImageRepository extends JpaRepository<FieldImage, Long> {
}
