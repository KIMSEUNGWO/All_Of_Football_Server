package com.flutter.alloffootball.admin.dto.field;

import com.flutter.alloffootball.common.domain.field.Field;
import com.flutter.alloffootball.common.enums.field.Parking;
import com.flutter.alloffootball.common.enums.field.Shower;
import com.flutter.alloffootball.common.enums.field.Toilet;
import com.flutter.alloffootball.common.enums.region.Region;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class FieldForm {

    private String title;
    private Region region;
    private String address;

    private int sizeX;
    private int sizeY;

    private Parking parking;
    private Toilet toilet;
    private Shower shower;

    private String description;

    public FieldForm(Field field) {
        title = field.getTitle();
        region = field.getAddress().getRegion();
        address = field.getAddress().getAddress();
        sizeX = field.getFieldData().getSizeX();
        sizeY = field.getFieldData().getSizeY();
        parking = field.getFieldData().getParking();
        toilet = field.getFieldData().getToilet();
        shower = field.getFieldData().getShower();
        description = field.getDescription();
    }
}