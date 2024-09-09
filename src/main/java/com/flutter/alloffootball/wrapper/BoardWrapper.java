package com.flutter.alloffootball.wrapper;

import com.flutter.alloffootball.common.domain.board.Board;
import com.flutter.alloffootball.dto.board.ResponseBoard;
import com.flutter.alloffootball.dto.board.ResponseBoardDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardWrapper {

    private final UserWrapper userWrapper;
    private final MatchWrapper matchWrapper;

    public ResponseBoard boardWrap(Board board) {
        return ResponseBoard.builder()
            .boardId(board.getId())
            .title(board.getTitle())
            .region(board.getRegion())
            .createDate(board.getCreateDate())
            .user(userWrapper.boardUserWrap(board.getUser()))
            .build();
    }
    public ResponseBoardDetail boardDetailWrap(Board board) {
        return ResponseBoardDetail.builder()
            .boardId(board.getId())
            .title(board.getTitle())
            .content(board.getContent())
            .region(board.getRegion())
            .createDate(board.getCreateDate())
            .isUpdated(board.getUpdateDate() != null)
            .user(userWrapper.boardUserWrap(board.getUser()))
            .match(board.getMatch() == null ? null : matchWrapper.matchViewWrap(board.getMatch()))
            .build();
    }
}
