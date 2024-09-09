package com.flutter.alloffootball.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ResponseBoardUser {

    private final long userId;
    private final String image;
    private final String nickname;
}
