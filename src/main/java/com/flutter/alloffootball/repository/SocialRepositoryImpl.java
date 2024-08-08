package com.flutter.alloffootball.repository;

import com.flutter.alloffootball.domain.user.User;
import com.flutter.alloffootball.enums.Provider;
import com.flutter.alloffootball.jparepository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SocialRepositoryImpl implements SocialRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public Optional<User> findBySocialIdAndProvider(String socialId, Provider provider) {
        return jpaUserRepository.findBySocial_SocialIdAndSocial_Provider(socialId, provider);
    }

    @Override
    public Optional<User> findByRefreshToken(String refreshToken) {
        return jpaUserRepository.findBySocial_RefreshToken(refreshToken);
    }
}
