package com.flutter.alloffootball.dto.board;

import com.flutter.alloffootball.common.enums.region.Region;
import com.flutter.alloffootball.dto.user.ResponseBoardUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ResponseBoard {

    private final Long boardId;
    private final String title;
    private final Region region;
    private final LocalDateTime createDate;

    private final ResponseBoardUser user;
}
