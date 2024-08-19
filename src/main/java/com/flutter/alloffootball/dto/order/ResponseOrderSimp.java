package com.flutter.alloffootball.dto.order;

import com.flutter.alloffootball.common.domain.field.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
public class ResponseOrderSimp {

    final String title;
    final int totalPrice;
    final int matchHour;

    final Address address;
    final LocalDateTime matchDate;

    final int cash;
    final int couponCount;
}
