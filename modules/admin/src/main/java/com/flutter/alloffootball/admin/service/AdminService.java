package com.flutter.alloffootball.admin.service;

import com.flutter.alloffootball.admin.dto.field.*;
import com.flutter.alloffootball.admin.dto.match.*;
import com.flutter.alloffootball.admin.dto.user.RequestSearchUser;
import com.flutter.alloffootball.admin.dto.user.ResponseSearchUser;
import com.flutter.alloffootball.admin.dto.user.ResponseUserOrder;
import com.flutter.alloffootball.admin.dto.user.ResponseViewUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {
    Page<ResponseSearchField> findAllBySearchField(RequestSearchField data, Pageable pageable);
    Page<ResponseSearchMatch> findAllBySearchMatch(RequestSearchMatch data, Pageable pageable);
    Page<ResponseSearchUser> findAllBySearchUser(RequestSearchUser data, Pageable pageable);

    void saveField(RequestSaveFieldForm saveFieldForm);

    ResponseViewField findByIdViewField(long fieldId);
    ResponseViewMatch findByIdViewMatch(long matchId);

    ResponseEditField getEditFieldForm(Long fieldId);

    void patchEditField(Long fieldId, ResponseEditField editField);

    ResponseFieldSimpInfo findByIdFieldSimpInfo(long fieldId);

    long createMatch(long fieldId, RequestSaveMatchForm form);

    ResponseViewUser findByIdViewUser(long userId);

    Page<ResponseUserOrder> findAllByUserOrder(Long userId, Pageable pageable);
}
