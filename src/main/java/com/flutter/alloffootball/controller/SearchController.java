package com.flutter.alloffootball.controller;

import com.flutter.alloffootball.admin.dto.ResponseSearchField;
import com.flutter.alloffootball.common.dto.Response;
import com.flutter.alloffootball.dto.match.RequestSearchMatch;
import com.flutter.alloffootball.dto.match.ResponseMatchView;
import com.flutter.alloffootball.service.FieldService;
import com.flutter.alloffootball.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final MatchService matchService;
    private final FieldService fieldService;

    /** 완료
     * 조건에 맞는 경기 목록 조회 ( 권한 필요없음 )
     */
    @GetMapping
    public ResponseEntity<Response> getMatchList(@ModelAttribute RequestSearchMatch searchMatch,
                                                 Pageable pageable) {
        System.out.println("searchMatch = " + searchMatch);
        List<ResponseMatchView> matchList = matchService.findAllByMatchData(searchMatch, pageable);
        return Response.ok(matchList);
    }

    @GetMapping("/field")
    public ResponseEntity<Response> searchField(@RequestParam("word") String word) {
        List<ResponseSearchField> fields = fieldService.search(word);
        return Response.ok(fields);
    }


}
