package com.flutter.alloffootball.admin.dto.match;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ResponseViewMatchUser {

    private final long userId;
    private final String nickname;
    private final int price; // 결제금액
    private final LocalDateTime createDate;
}
