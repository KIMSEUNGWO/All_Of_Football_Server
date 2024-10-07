package com.flutter.alloffootball.admin.service;

import com.flutter.alloffootball.admin.dto.*;
import com.flutter.alloffootball.admin.dto.field.ResponseEditField;
import com.flutter.alloffootball.common.enums.MatchStatus;
import com.flutter.alloffootball.common.enums.SexType;
import com.flutter.alloffootball.common.enums.region.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {
    Page<ResponseSearchField> findAllBySearchField(RequestSearchField data, Pageable pageable);
    Page<ResponseSearchMatch> findAllBySearchMatch(RequestSearchMatch data, Pageable pageable);

    void saveField(RequestSaveFieldForm saveFieldForm);

    ResponseViewField findByIdViewField(long fieldId);

    ResponseEditField getEditFieldForm(Long fieldId);

    void patchEditField(Long fieldId, ResponseEditField editField);
}
