package com.flutter.alloffootball.component;

import com.flutter.alloffootball.config.security.CustomUserDetails;
import com.flutter.alloffootball.domain.user.User;
import com.flutter.alloffootball.dto.login.RegisterRequest;
import com.flutter.alloffootball.dto.login.SocialLoginDto;
import com.flutter.alloffootball.enums.Provider;
import com.flutter.alloffootball.exception.TokenError;
import com.flutter.alloffootball.exception.TokenException;
import com.flutter.alloffootball.repository.SocialRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUtil {

    private final JwtUtil jwtUtil;
    private final SocialRepository socialRepository;


    public void saveUserInSecurityContext(String accessToken) {
        String socialId = jwtUtil.extractClaim(accessToken,  Claims::getSubject);
        String socialProvider = jwtUtil.extractClaim(accessToken, Claims::getIssuer);
        saveUserInSecurityContext(socialId, Provider.from(socialProvider));
    }

    public void saveUserInSecurityContext(String socialId, Provider provider) {
        if (socialId == null || provider == null) throw new TokenException(TokenError.TOKEN_EXPIRED);

        UserDetails userDetails = loadUserBySocialIdAndSocialProvider(socialId, provider);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

    private UserDetails loadUserBySocialIdAndSocialProvider(String socialId, Provider provider) {
        return socialRepository.findBySocialIdAndProvider(socialId, provider)
            .map(CustomUserDetails::new)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private UsernamePasswordAuthenticationToken getAuthentication(User user) {
        UserDetails userDetails = new CustomUserDetails(user);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }



}