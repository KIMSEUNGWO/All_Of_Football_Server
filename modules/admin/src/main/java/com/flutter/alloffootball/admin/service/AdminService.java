package com.flutter.alloffootball.admin.service;

import com.flutter.alloffootball.admin.dto.*;
import com.flutter.alloffootball.admin.dto.field.*;
import com.flutter.alloffootball.admin.dto.match.RequestSaveMatchForm;
import com.flutter.alloffootball.admin.dto.match.ResponseViewMatch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {
    Page<ResponseSearchField> findAllBySearchField(RequestSearchField data, Pageable pageable);
    Page<ResponseSearchMatch> findAllBySearchMatch(RequestSearchMatch data, Pageable pageable);

    void saveField(RequestSaveFieldForm saveFieldForm);

    ResponseViewField findByIdViewField(long fieldId);
    ResponseViewMatch findByIdViewMatch(long matchId);

    ResponseEditField getEditFieldForm(Long fieldId);

    void patchEditField(Long fieldId, ResponseEditField editField);

    ResponseFieldSimpInfo findByIdFieldSimpInfo(long fieldId);

    long createMatch(long fieldId, RequestSaveMatchForm form);
}
