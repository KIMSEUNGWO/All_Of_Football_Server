package com.flutter.alloffootball.controller;

import com.flutter.alloffootball.common.dto.Response;
import com.flutter.alloffootball.dto.field.ResponseFieldData;
import com.flutter.alloffootball.dto.match.ResponseMatchData;
import com.flutter.alloffootball.service.FieldService;
import com.flutter.alloffootball.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/field")
public class FieldController {

    private final MatchService matchService;
    private final FieldService fieldService;

    /**
     * 구장 상세정보 조회 ( 권한 필요없음 )
     */
    @GetMapping("/{fieldId}")
    public ResponseEntity<Response> fieldDetails(@PathVariable("fieldId") long fieldId) {
        ResponseFieldData fieldData = fieldService.getFieldDetails(fieldId);
        return Response.ok(fieldData);
    }

    /**
     * 구장별 경기목록 조회 ( 권한 필요없음 )
     */
    @GetMapping("/{fieldId}/schedule")
    public ResponseEntity<Response> fieldSchedule(@PathVariable("fieldId") long fieldId, Pageable pageable) {
        List<ResponseMatchData> matchDataList = matchService.findAllByFieldIdToMatchData(fieldId, pageable);
        return Response.ok(matchDataList);
    }
}
