package com.flutter.alloffootball.controller;

import com.flutter.alloffootball.common.dto.Response;
import com.flutter.alloffootball.dto.match.RequestSearchMatch;
import com.flutter.alloffootball.dto.match.ResponseMatchView;
import com.flutter.alloffootball.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final MatchService matchService;

    /** 완료
     * 조건에 맞는 경기 목록 조회 ( 권한 필요없음 )
     */
    @GetMapping("/search")
    public ResponseEntity<Response> getMatchList(@ModelAttribute RequestSearchMatch searchMatch,
                                                 Pageable pageable) {
        System.out.println("searchMatch = " + searchMatch);
        List<ResponseMatchView> matchList = matchService.findAllByMatchData(searchMatch, pageable);
        return Response.ok(matchList);
    }
}
