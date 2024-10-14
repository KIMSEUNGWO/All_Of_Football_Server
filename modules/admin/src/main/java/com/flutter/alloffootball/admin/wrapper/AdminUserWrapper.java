package com.flutter.alloffootball.admin.wrapper;

import com.flutter.alloffootball.admin.dto.user.ResponseSearchUser;
import com.flutter.alloffootball.admin.dto.user.ResponseViewUser;
import com.flutter.alloffootball.common.domain.user.User;
import com.flutter.alloffootball.common.enums.SexType;
import org.springframework.stereotype.Component;

@Component
public class AdminUserWrapper {

    public ResponseSearchUser searchUserWrap(User user) {
        return ResponseSearchUser.builder()
            .userId(user.getId())
            .nickname(user.getNickname())
            .social(user.getSocial().getProvider())
            .sex(user.getUserInfo().getSex() == SexType.MALE ? "남자" : "여자")
            .birth(user.getUserInfo().getBirth())
            .createDate(user.getCreateDate())
            .status("정상")
            .build();
    }

    public ResponseViewUser viewUserWrap(User user) {
        return ResponseViewUser.builder()
            .userId(user.getId())
            .profile(user.getProfile().getOriginalName())
            .nickname(user.getNickname())
            .social(user.getSocial().getProvider())
            .birth(user.getUserInfo().getBirth())
            .cash(user.getCash())
            .createDate(user.getCreateDate())
            .sex(user.getUserInfo().getSex())
            .status("정상")
            .build();
    }
}
