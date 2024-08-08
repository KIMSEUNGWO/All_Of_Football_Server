package com.flutter.alloffootball.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Authority {

    SUPER_ADMIN("최고관리자"),
    MATCH_MANAGER("경기담당자"),
    HR_MANAGER("인사담당자"),
    INQUIRE_MANAGER("문의담당자");

    private final String ko;
}
