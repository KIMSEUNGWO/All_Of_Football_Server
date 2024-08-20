package com.flutter.alloffootball.controller;

import com.flutter.alloffootball.common.config.security.CustomUserDetails;
import com.flutter.alloffootball.common.dto.Response;
import com.flutter.alloffootball.dto.match.ResponseMatchView;
import com.flutter.alloffootball.dto.user.RequestCalendar;
import com.flutter.alloffootball.dto.user.ResponseUserProfile;
import com.flutter.alloffootball.service.OrderService;
import com.flutter.alloffootball.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final OrderService orderService;

    @GetMapping("/profile")
    public ResponseEntity<Response> profile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        ResponseUserProfile userProfile = userService.getUserProfile(userDetails.getUser().getId());
        return Response.ok(userProfile);
    }

    @GetMapping("/history")
    public ResponseEntity<Response> calendar(@ModelAttribute RequestCalendar calendar, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Map<Integer, List<ResponseMatchView>> histories = orderService.getHistory(calendar.getDate(), userDetails.getUser());
        return Response.ok(histories);
    }
}
