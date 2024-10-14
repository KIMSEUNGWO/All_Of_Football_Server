package com.flutter.alloffootball.admin.dto.user;

import com.flutter.alloffootball.common.enums.Provider;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class ResponseSearchUser {

    private final Long userId;
    private final String nickname;
    private final Provider social;
    private final String sex;
    private final LocalDate birth;
    private final LocalDateTime createDate;
    private final String status;
}
