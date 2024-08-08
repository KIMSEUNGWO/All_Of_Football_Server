package com.flutter.alloffootball.repository;

import com.flutter.alloffootball.domain.user.User;
import com.flutter.alloffootball.enums.Provider;

import java.util.Optional;

public interface SocialRepository {

    Optional<User> findBySocialIdAndProvider(String socialId, Provider provider);

    Optional<User> findByRefreshToken(String refreshToken);
}
