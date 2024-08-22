package com.flutter.alloffootball.common.jparepository;

import com.flutter.alloffootball.common.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaFavoriteRepository extends JpaRepository<Favorite, Long> {

    boolean existsByUser_IdAndField_Id(Long userId, Long fieldId);
    List<Favorite> findAllByUser_Id(Long userId);
    void deleteByUser_IdAndField_Id(Long userId, Long fieldId);
}
