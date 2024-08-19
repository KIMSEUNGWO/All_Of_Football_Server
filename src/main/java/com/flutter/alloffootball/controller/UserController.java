package com.flutter.alloffootball.controller;

import com.flutter.alloffootball.common.config.security.CustomUserDetails;
import com.flutter.alloffootball.common.dto.Response;
import com.flutter.alloffootball.dto.match.ResponseMatchView;
import com.flutter.alloffootball.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {


    private final OrderService orderService;

    // TODO 여기서부터하면됨
    @GetMapping("/profile")
    public ResponseEntity<Response> profile(@AuthenticationPrincipal CustomUserDetails userDetails) {

        return Response.ok();
    }

    @GetMapping("/calendar")
    public ResponseEntity<Response> calendar(@ModelAttribute LocalDateTime date, @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<Integer> existsDays = orderService.getCalendar(date, userDetails.getUser());
        return Response.ok(existsDays);
    }
    @GetMapping("/history")
    public ResponseEntity<Response> history(@ModelAttribute LocalDateTime date, @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<ResponseMatchView> histories = orderService.getHistory(date, userDetails.getUser());
        return Response.ok(histories);
    }
}
