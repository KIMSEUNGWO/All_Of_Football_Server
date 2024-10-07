package com.flutter.alloffootball.admin.repository;

import com.flutter.alloffootball.admin.dto.RequestSearchField;
import com.flutter.alloffootball.admin.dto.RequestSearchMatch;
import com.flutter.alloffootball.admin.dto.ResponseSearchField;
import com.flutter.alloffootball.admin.dto.ResponseSearchMatch;
import com.flutter.alloffootball.common.domain.Admin;
import com.flutter.alloffootball.common.enums.MatchStatus;
import com.flutter.alloffootball.common.enums.SexType;
import com.flutter.alloffootball.common.enums.region.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminRepository {

    Admin findByAccount(String account);

    Page<ResponseSearchField> findAllBySearchField(RequestSearchField data, Pageable pageable);

    Page<ResponseSearchMatch> findAllBySearchMatch(RequestSearchMatch data, Pageable pageable);
}
