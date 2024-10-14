package com.flutter.alloffootball.admin.repository;

import com.flutter.alloffootball.admin.dto.field.RequestSearchField;
import com.flutter.alloffootball.admin.dto.match.RequestSearchMatch;
import com.flutter.alloffootball.admin.dto.field.ResponseSearchField;
import com.flutter.alloffootball.admin.dto.match.ResponseSearchMatch;
import com.flutter.alloffootball.admin.dto.user.RequestSearchUser;
import com.flutter.alloffootball.admin.dto.user.ResponseSearchUser;
import com.flutter.alloffootball.common.domain.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminRepository {

    Admin findByAccount(String account);

    Page<ResponseSearchField> findAllBySearchField(RequestSearchField data, Pageable pageable);

    Page<ResponseSearchMatch> findAllBySearchMatch(RequestSearchMatch data, Pageable pageable);

    Page<ResponseSearchUser> findAllBySearchUser(RequestSearchUser data, Pageable pageable);
}
