package com.flutter.alloffootball.dto.match;

import com.flutter.alloffootball.domain.field.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
public class ResponseMatchOrder {

    private final String title;
    private final Address address;
    private final LocalDateTime matchDate;
    private final int matchTime;
    private final int hourPrice;

    private final int cash;
}
