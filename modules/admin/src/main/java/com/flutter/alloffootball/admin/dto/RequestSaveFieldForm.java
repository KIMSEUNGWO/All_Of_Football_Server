package com.flutter.alloffootball.admin.dto;

import com.flutter.alloffootball.common.enums.field.Parking;
import com.flutter.alloffootball.common.enums.field.Shower;
import com.flutter.alloffootball.common.enums.field.Toilet;
import com.flutter.alloffootball.common.enums.region.Region;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
public class RequestSaveFieldForm {

    private List<MultipartFile> images;

    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private Region region;
    @NotNull
    private String address;

    @NotNull
    private int sizeX;
    @NotNull
    private int sizeY;

    @NotNull
    private Parking parking;
    @NotNull
    private Toilet toilet;
    @NotNull
    private Shower shower;

    private double lng;
    private double lat;

    @Min(1000)
    private int hourPrice; // 구장 시간 당 가격
}
