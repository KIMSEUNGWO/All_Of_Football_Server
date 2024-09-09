package com.flutter.alloffootball.controller;

import com.flutter.alloffootball.common.config.security.CustomUserDetails;
import com.flutter.alloffootball.common.domain.board.Board;
import com.flutter.alloffootball.common.dto.Response;
import com.flutter.alloffootball.common.exception.BindingException;
import com.flutter.alloffootball.dto.board.RequestCreateBoard;
import com.flutter.alloffootball.dto.board.ResponseBoardDetail;
import com.flutter.alloffootball.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{boardId}")
    public ResponseEntity<Response> getBoardDetail(@PathVariable("boardId") Long boardId) {
        ResponseBoardDetail boardDetail = boardService.findBoardDetail(boardId);
        return Response.ok(boardDetail);
    }

    @PostMapping("/method")
    public ResponseEntity<Response> addBoard(@Validated @RequestBody RequestCreateBoard createBoard,
                                             BindingResult bindingResult,
                                             @AuthenticationPrincipal CustomUserDetails userDetails) {
        System.out.println("createBoard = " + createBoard);
        if (bindingResult.hasErrors()) throw new BindingException(bindingResult);

        boardService.createBoard(createBoard, userDetails.getUser().getId());
        return Response.ok();
    }

}
