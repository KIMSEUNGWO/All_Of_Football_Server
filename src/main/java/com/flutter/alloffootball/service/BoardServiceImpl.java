package com.flutter.alloffootball.service;

import com.flutter.alloffootball.common.domain.board.Board;
import com.flutter.alloffootball.common.domain.match.Match;
import com.flutter.alloffootball.common.domain.user.User;
import com.flutter.alloffootball.common.jparepository.JpaMatchRepository;
import com.flutter.alloffootball.dto.board.RequestCreateBoard;
import com.flutter.alloffootball.dto.board.RequestSearchBoard;
import com.flutter.alloffootball.dto.board.ResponseBoard;
import com.flutter.alloffootball.dto.board.ResponseBoardDetail;
import com.flutter.alloffootball.repository.BoardRepository;
import com.flutter.alloffootball.repository.UserRepository;
import com.flutter.alloffootball.wrapper.BoardWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

    private final UserRepository userRepository;
    private final JpaMatchRepository jpaMatchRepository;
    private final BoardRepository boardRepository;
    private final BoardWrapper boardWrapper;

    @Override
    public List<ResponseBoard> search(RequestSearchBoard searchBoard, Pageable pageable) {
        return boardRepository.search(searchBoard, pageable).stream()
            .map(boardWrapper::boardWrap)
            .toList();
    }

    @Override
    public ResponseBoardDetail findBoardDetail(Long boardId) {
        Board board = boardRepository.findById(boardId);
        return boardWrapper.boardDetailWrap(board);
    }

    @Override
    public void createBoard(RequestCreateBoard createBoard, Long userId) {
        User user = userRepository.findById(userId);
        Match match = null;
        if (createBoard.getMatchId() != null) {
            match = jpaMatchRepository.findById(createBoard.getMatchId()).orElse(null);
        }

        Board saveBoard = Board.builder()
            .user(user)
            .match(match)
            .title(createBoard.getTitle())
            .content(createBoard.getContent())
            .region(createBoard.getRegion())
            .build();
        boardRepository.save(saveBoard);
    }
}
