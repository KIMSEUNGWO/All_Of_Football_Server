package com.flutter.alloffootball.controller;

import com.flutter.alloffootball.common.config.security.CustomUserDetails;
import com.flutter.alloffootball.common.dto.Response;
import com.flutter.alloffootball.dto.match.RequestSearchMatch;
import com.flutter.alloffootball.dto.match.ResponseMatchView;
import com.flutter.alloffootball.dto.match.ResponseMatchDetails;
import com.flutter.alloffootball.dto.match.ResponseMatchOrder;
import com.flutter.alloffootball.common.exception.BindingException;
import com.flutter.alloffootball.dto.order.ResponseOrderSimp;
import com.flutter.alloffootball.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;

    /** 완료
     * 조건에 맞는 경기 목록 조회 ( 권한 필요없음 )
     */
    @GetMapping
    public ResponseEntity<Response> getMatchList(@ModelAttribute @Validated RequestSearchMatch searchMatch,
                                                 BindingResult bindingResult,
                                                 Pageable pageable) {
        System.out.println("searchMatch = " + searchMatch);
        if (bindingResult.hasErrors()) {
            throw new BindingException(bindingResult);
        }
        List<ResponseMatchView> matchList = matchService.findAllByMatchData(searchMatch, pageable);
        return Response.ok(matchList);
    }

    /** 완료
     * 경기 상세정보 조회 ( 권한 필요없음 )
     */
    @GetMapping("/{matchId}")
    public ResponseEntity<Response> getMatchDetails(@PathVariable long matchId,
                                                    @AuthenticationPrincipal CustomUserDetails userDetails) {
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
