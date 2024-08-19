package com.flutter.alloffootball.common.jparepository;

import com.flutter.alloffootball.common.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFavoriteRepository extends JpaRepository<Favorite, Long> {

    boolean existsByUser_IdAndField_Id(Long userId, Long fieldId);
}
