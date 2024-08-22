package com.flutter.alloffootball.repository;

import com.flutter.alloffootball.common.domain.Favorite;
import com.flutter.alloffootball.common.jparepository.JpaFavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class FavoriteRepositoryImpl implements FavoriteRepository {

    private final JpaFavoriteRepository jpaFavoriteRepository;

    @Override
    public void save(Favorite favorite) {
        jpaFavoriteRepository.save(favorite);
    }

    @Override
    public void deleteByUserIdAndFieldId(Long userId, Long fieldId) {
        if (userId == null || fieldId == null) return;
        jpaFavoriteRepository.deleteByUser_IdAndField_Id(userId, fieldId);
    }
}
