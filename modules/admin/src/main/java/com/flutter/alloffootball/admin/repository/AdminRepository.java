package com.flutter.alloffootball.admin.repository;

import com.flutter.alloffootball.admin.dto.field.RequestSearchField;
import com.flutter.alloffootball.admin.dto.RequestSearchMatch;
import com.flutter.alloffootball.admin.dto.field.ResponseSearchField;
import com.flutter.alloffootball.admin.dto.ResponseSearchMatch;
import com.flutter.alloffootball.common.domain.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminRepository {

    Admin findByAccount(String account);

    Page<ResponseSearchField> findAllBySearchField(RequestSearchField data, Pageable pageable);

    Page<ResponseSearchMatch> findAllBySearchMatch(RequestSearchMatch data, Pageable pageable);
}
