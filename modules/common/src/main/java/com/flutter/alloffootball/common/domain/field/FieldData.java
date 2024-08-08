package com.flutter.alloffootball.common.domain.field;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FieldData {

    private char parking;
    private char shower;
    private char toilet;

    private int sizeX;
    private int sizeY;

    private int hourPrice; // 구장 시간 당 가격

}
