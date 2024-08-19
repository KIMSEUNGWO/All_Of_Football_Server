package com.flutter.alloffootball.controller;

import com.flutter.alloffootball.api.SocialProfile;
import com.flutter.alloffootball.api.SocialVerifier;
import com.flutter.alloffootball.common.component.JwtUtil;
import com.flutter.alloffootball.common.component.ResponseToken;
import com.flutter.alloffootball.common.component.SecurityUtil;
import com.flutter.alloffootball.common.domain.user.User;
import com.flutter.alloffootball.common.dto.Response;
import com.flutter.alloffootball.dto.login.RegisterRequest;
import com.flutter.alloffootball.common.exception.BindingException;
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

    @PostMapping("/register")
    public ResponseEntity<Response> register(@Validated @RequestBody RegisterRequest registerRequest,
                                             BindingResult bindingResult) {
        System.out.println("registerRequest = " + registerRequest);
        if (bindingResult.hasErrors()) throw new BindingException(bindingResult);

        // Line API 에서 사용자 정보 검증 및 가져오기
        SocialProfile profile = socialVerifier.getProfile(registerRequest.getSocialId(), registerRequest.getProvider(), registerRequest.getAccessToken());

        ResponseToken responseToken = registerService.register(registerRequest, profile);

        System.out.println("responseToken = " + responseToken);
        return Response.ok(responseToken);
    }
}
