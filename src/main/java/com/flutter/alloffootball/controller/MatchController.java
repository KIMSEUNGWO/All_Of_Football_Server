package com.flutter.alloffootball.controller;

import com.flutter.alloffootball.common.component.UserDetailsUtil;
import com.flutter.alloffootball.common.config.security.CustomUserDetails;
import com.flutter.alloffootball.common.dto.Response;
import com.flutter.alloffootball.dto.match.ResponseMatchDetails;
import com.flutter.alloffootball.dto.match.ResponseMatchOrder;
import com.flutter.alloffootball.dto.order.ResponseOrderSimp;
import com.flutter.alloffootball.service.MatchService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;
    private final UserDetailsUtil userDetailsUtil;

    /** 완료
     * 경기 상세정보 조회 ( 권한 필요없음 )
     */
    @GetMapping("/{matchId}")
    public ResponseEntity<Response> getMatchDetails(@PathVariable("matchId") long matchId, HttpServletRequest request) {
        CustomUserDetails userDetails = userDetailsUtil.getUserDetails(request);
        ResponseMatchDetails matchDetails = matchService.getMatchDetails(matchId, userDetails);
        return Response.ok(matchDetails);
    }

    /**
     * 경기 신청
     */
    @GetMapping("/{matchId}/order")
    public ResponseEntity<Response> orderGet(@PathVariable long matchId,
                                            @AuthenticationPrincipal CustomUserDetails userDetails) {
        ResponseOrderSimp matchDetails = matchService.getOrderSimp(matchId, userDetails);
        return Response.ok(matchDetails);
    }



    /**
     * 경기신청 전 경기 및 유저 캐시 조회 ( 유저 이상 권한 필요 )
     */
    @GetMapping("/{matchId}/order/detail")
    public ResponseEntity<Response> getMatchOrder(@PathVariable long matchId,
                                                  @AuthenticationPrincipal CustomUserDetails userDetails) {
        ResponseMatchOrder matchOrder = matchService.getMatchOrder(matchId, userDetails);
        return Response.ok(matchOrder);
    }


}
