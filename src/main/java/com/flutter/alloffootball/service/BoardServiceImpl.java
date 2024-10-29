package com.flutter.alloffootball.service;

import com.flutter.alloffootball.common.domain.board.Board;
import com.flutter.alloffootball.common.domain.match.Match;
import com.flutter.alloffootball.common.domain.user.User;
import com.flutter.alloffootball.common.exception.CustomRuntimeException;
import com.flutter.alloffootball.common.exception.DefaultError;
import com.flutter.alloffootball.common.jparepository.JpaMatchRepository;
import com.flutter.alloffootball.dto.board.*;
import com.flutter.alloffootball.repository.BoardRepository;
import com.flutter.alloffootball.repository.UserRepository;
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

    @Override
    public List<ResponseBoard> search(RequestSearchBoard searchBoard, Pageable pageable) {
        return boardRepository.search(searchBoard, pageable).stream()
            .map(ResponseBoard::new)
            .toList();
    }

    @Override
    public ResponseBoardDetail findBoardDetail(Long boardId) {
        Board board = boardRepository.findById(boardId);
        return new ResponseBoardDetail(board);
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

    @Override
    public void editBoard(RequestEditBoard editBoard, Long userId) {
        Board board = boardRepository.findById(editBoard.getBoardId());
        // 자신의 게시물이 아닌 경우
        if (!board.getUser().getId().equals(userId)) {
            throw new CustomRuntimeException(DefaultError.NOT_AUTHENTICATION);
        }
        Match match = null;
        if (editBoard.getMatchId() != null) {
            match = jpaMatchRepository.findById(editBoard.getMatchId()).orElse(null);
        }

        board.update(editBoard.getTitle(), editBoard.getContent(), editBoard.getRegion(), match);
    }

    @Override
    public void deleteBoard(RequestDeleteBoard deleteBoard, Long userId) {
        Board board = boardRepository.findById(deleteBoard.getBoardId());
        // 자신의 게시물이 아닌 경우
        if (!board.getUser().getId().equals(userId)) {
            throw new CustomRuntimeException(DefaultError.NOT_AUTHENTICATION);
        }

        boardRepository.delete(board);
    }
}
