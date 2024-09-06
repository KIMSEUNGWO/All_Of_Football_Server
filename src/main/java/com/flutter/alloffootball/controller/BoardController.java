package com.flutter.alloffootball.controller;

import com.flutter.alloffootball.common.dto.Response;
import com.flutter.alloffootball.dto.board.ResponseBoardDetail;
import com.flutter.alloffootball.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/{boardId}")
    public ResponseEntity<Response> getBoardDetail(@PathVariable("boardId") Long boardId) {
        ResponseBoardDetail boardDetail = boardService.findBoardDetail(boardId);
        return Response.ok(boardDetail);
    }
}
