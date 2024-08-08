package com.flutter.alloffootball.repository;

import com.flutter.alloffootball.domain.user.User;


public interface UserRepository {
    User findByRefreshToken(String refreshToken);

    User save(User user);

    User findById(long userId);
}
