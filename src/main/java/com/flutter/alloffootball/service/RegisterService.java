package com.flutter.alloffootball.service;

import com.flutter.alloffootball.api.SocialProfile;
import com.flutter.alloffootball.domain.user.User;
import com.flutter.alloffootball.dto.login.RegisterRequest;

public interface RegisterService {
    User register(RegisterRequest registerRequest, SocialProfile profile);
}
