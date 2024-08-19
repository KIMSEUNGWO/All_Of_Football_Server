package com.flutter.alloffootball.dto.cash;

import com.flutter.alloffootball.common.enums.CashType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
public class ResponseReceipt {

    private final String title;
    private final CashType type;
    private final LocalDateTime date;
    private final int useCash;
    private final int remainCash;
}
