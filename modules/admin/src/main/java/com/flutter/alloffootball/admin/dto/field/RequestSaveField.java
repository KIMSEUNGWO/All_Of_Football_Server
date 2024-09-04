package com.flutter.alloffootball.admin.dto.field;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
public class RequestSaveField extends FieldForm {

    private List<MultipartFile> images;


}
