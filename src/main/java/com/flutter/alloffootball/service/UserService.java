package com.flutter.alloffootball.service;

import com.flutter.alloffootball.dto.user.ResponseUserProfile;

public interface UserService {
    ResponseUserProfile getUserProfile(Long userId);
}
