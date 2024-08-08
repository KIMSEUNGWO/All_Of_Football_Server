package com.flutter.alloffootball.dto.order;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestOrder {

    @NotNull
    private Long matchId;

    private Long couponId;
}
