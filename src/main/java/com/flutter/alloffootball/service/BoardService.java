package com.flutter.alloffootball.service;

import com.flutter.alloffootball.dto.board.RequestSearchBoard;
import com.flutter.alloffootball.dto.board.ResponseBoard;
import com.flutter.alloffootball.dto.board.ResponseBoardDetail;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    List<ResponseBoard> search(RequestSearchBoard searchBoard, Pageable pageable);

    ResponseBoardDetail findBoardDetail(Long boardId);
}
