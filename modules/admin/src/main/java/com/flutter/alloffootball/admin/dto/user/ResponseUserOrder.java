package com.flutter.alloffootball.admin.dto.user;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ResponseUserOrder {

    private final long orderId;

    private final String title;
    private final long matchId;
    private final int price;
    private final LocalDateTime cancelDate;
    private final LocalDateTime createDate;
}
