package com.flutter.alloffootball.dto.match;

import com.flutter.alloffootball.common.annotation.DateRange;
import com.flutter.alloffootball.common.enums.SexType;
import com.flutter.alloffootball.common.enums.region.Region;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RequestSearchMatch {

    private Region region;

    @DateRange(maxDays = 14)
    private LocalDate date;

    private SexType sex;

}
