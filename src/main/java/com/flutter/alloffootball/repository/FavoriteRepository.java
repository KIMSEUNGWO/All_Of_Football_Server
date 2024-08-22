package com.flutter.alloffootball.repository;

import com.flutter.alloffootball.common.domain.Favorite;

public interface FavoriteRepository {

    void save(Favorite favorite);

    void deleteByUserIdAndFieldId(Long userId, Long fieldId);
}
