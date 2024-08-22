package com.flutter.alloffootball.service;

import com.flutter.alloffootball.common.domain.user.User;
import com.flutter.alloffootball.dto.user.ResponseUserProfile;
import com.flutter.alloffootball.repository.UserRepository;
import com.flutter.alloffootball.wrapper.UserWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserWrapper userWrapper;

    @Override
    public ResponseUserProfile getUserProfile(Long userId) {
        User user = userRepository.findById(userId);
        return userWrapper.userProfileWrap(user);
    }

    @Override
    public boolean distinctNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }
}
