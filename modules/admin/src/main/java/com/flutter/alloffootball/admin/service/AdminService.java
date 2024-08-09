package com.flutter.alloffootball.admin.service;

import com.flutter.alloffootball.admin.dto.ResponseSearchField;
import com.flutter.alloffootball.common.enums.region.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {
    Page<ResponseSearchField> findAllBySearch(Region region, String word, Pageable pageable);
}