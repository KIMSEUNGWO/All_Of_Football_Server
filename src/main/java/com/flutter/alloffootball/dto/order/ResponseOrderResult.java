package com.flutter.alloffootball.dto.order;

import com.flutter.alloffootball.dto.coupon.ResponseCouponUse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.Nullable;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class ResponseOrderResult {

    // 이용 금액
    private final int totalPrice;

    @Nullable
    private ResponseCouponUse coupon;

    // 최종 결재 금맥
    private final int finalPrice;

    // 남은 잔액
    private final int remainCash;
}
