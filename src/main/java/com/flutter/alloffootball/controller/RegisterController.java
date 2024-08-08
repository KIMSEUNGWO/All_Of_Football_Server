package com.flutter.alloffootball.controller;

import com.flutter.alloffootball.api.SocialProfile;
import com.flutter.alloffootball.api.SocialVerifier;
import com.flutter.alloffootball.component.JwtUtil;
import com.flutter.alloffootball.component.ResponseToken;
import com.flutter.alloffootball.component.SecurityUtil;
import com.flutter.alloffootball.domain.user.User;
import com.flutter.alloffootball.dto.Response;
import com.flutter.alloffootball.dto.login.RegisterRequest;
import com.flutter.alloffootball.exception.BindingException;
import com.flutter.alloffootball.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;
    private final SocialVerifier socialVerifier;
    private final SecurityUtil securityUtil;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@Validated @RequestBody RegisterRequest registerRequest,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new BindingException(bindingResult);

        // Line API 에서 사용자 정보 검증 및 가져오기
        SocialProfile profile = socialVerifier.getProfile(registerRequest.getSocialId(), registerRequest.getProvider(), registerRequest.getAccessToken());

        User register = registerService.register(registerRequest, profile);

        securityUtil.saveUserInSecurityContext(registerRequest.getAccessToken());
        ResponseToken responseToken = jwtUtil.initToken(register);

        System.out.println("responseToken = " + responseToken);
        return Response.ok(responseToken);
    }
}
