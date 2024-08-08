package com.flutter.alloffootball.jparepository;

import com.flutter.alloffootball.domain.field.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFieldRepository extends JpaRepository<Field, Long> {
}
