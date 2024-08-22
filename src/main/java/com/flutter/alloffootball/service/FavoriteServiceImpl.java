package com.flutter.alloffootball.service;

import com.flutter.alloffootball.common.domain.Favorite;
import com.flutter.alloffootball.dto.user.RequestFavoriteToggle;
import com.flutter.alloffootball.repository.FavoriteRepository;
import com.flutter.alloffootball.repository.FieldRepository;
import com.flutter.alloffootball.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class FavoriteServiceImpl implements FavoriteService {

    private final UserRepository userRepository;
    private final FieldRepository fieldRepository;
    private final FavoriteRepository favoriteRepository;

    @Override
    public void favoriteToggle(Long userId, RequestFavoriteToggle favoriteToggle) {
        boolean toggle = favoriteToggle.isToggle();

        if (toggle) { // 사용자가 즐겨찾기를 추가하면
            Favorite saveFavorite = Favorite.builder()
                .user(userRepository.findById(userId))
                .field(fieldRepository.findById(favoriteToggle.getFieldId()))
                .build();
            favoriteRepository.save(saveFavorite);
        } else { // 사용자가 즐겨찾기를 취소하면
            favoriteRepository.deleteByUserIdAndFieldId(userId, favoriteToggle.getFieldId());
        }
    }
}
