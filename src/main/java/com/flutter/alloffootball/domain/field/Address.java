package com.flutter.alloffootball.domain.field;

import com.flutter.alloffootball.enums.region.Region;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Embeddable
public class Address {

    private String address;

    @Enumerated(EnumType.STRING)
    private Region region;

    // 위도
    private double lat;
    // 경도
    private double lng;
}
