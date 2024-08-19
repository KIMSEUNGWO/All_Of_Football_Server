package com.flutter.alloffootball.wrapper;

import com.flutter.alloffootball.common.domain.Cash;
import com.flutter.alloffootball.dto.cash.ResponseReceipt;
import org.springframework.stereotype.Component;

@Component
public class CashWrapper {

    public ResponseReceipt receiptWrap(Cash cash) {
        return ResponseReceipt.builder()
            .title(cash.getDescription())
            .type(cash.getCashType())
            .date(cash.getCreateDate())
            .useCash(cash.getCashUse())
            .remainCash(cash.getCashNow())
            .build();
    }
}
