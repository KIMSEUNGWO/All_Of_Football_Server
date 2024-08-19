package com.flutter.alloffootball.dto.match;

import com.flutter.alloffootball.common.annotation.DateRange;
import com.flutter.alloffootball.common.enums.SexType;
import com.flutter.alloffootball.common.enums.region.Region;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class RequestSearchMatch {

    private Region region;

    @DateRange(maxDays = 30)
    private LocalDateTime date;

    private SexType sex;

}
