package com.flutter.alloffootball.dto.login;

import com.flutter.alloffootball.enums.Provider;
import com.flutter.alloffootball.enums.SexType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterRequest {

    @NotEmpty
    private String socialId;
    @NotNull
    private Provider provider;
    @NotNull
    private String accessToken;
    @NotNull
    private String nickname;
    @NotNull
    private SexType sex;
    @NotNull
    private LocalDate birth;
}
