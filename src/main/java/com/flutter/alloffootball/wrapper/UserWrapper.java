package com.flutter.alloffootball.wrapper;

import com.flutter.alloffootball.common.domain.user.User;
import com.flutter.alloffootball.dto.user.ResponseUserProfile;
import org.springframework.stereotype.Component;

@Component
public class UserWrapper {

    public ResponseUserProfile userProfileWrap(User user) {
        return ResponseUserProfile.builder()
            .id(user.getId())
            .image(user.getProfile().getThumbnailName())
            .nickname(user.getNickname())
            .sex(user.getUserInfo().getSex())
            .birth(user.getUserInfo().getBirth())
            .favoriteCount(user.getFavoriteList().size())
            .couponCount(user.possibleCouponList().size())
            .cash(user.getCash())
            .build();
    }
}
