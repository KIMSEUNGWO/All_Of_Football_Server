package com.flutter.alloffootball.repository;

import com.flutter.alloffootball.domain.user.User;
import com.flutter.alloffootball.exception.TokenError;
import com.flutter.alloffootball.exception.TokenException;
import com.flutter.alloffootball.exception.UserError;
import com.flutter.alloffootball.exception.UserException;
import com.flutter.alloffootball.jparepository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User findByRefreshToken(String refreshToken) {
        return jpaUserRepository.findBySocial_RefreshToken(refreshToken)
            .orElseThrow(() -> new TokenException(TokenError.TOKEN_EXPIRED));
    }

    @Override
    public User save(User user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public User findById(long userId) {
        return jpaUserRepository.findById(userId)
            .orElseThrow(() -> new UserException(UserError.USER_NOT_EXISTS));
    }
}
