package com.flutter.alloffootball.common.enums;

public enum CashType {

    CHARGE,
    USE,
    REFUND;

    public int accept(int receipt) {
        if (this == CHARGE || this == REFUND) return receipt;
        return -1 * receipt;
    }
}
