package com.flutter.alloffootball.admin.repository;

import com.flutter.alloffootball.admin.dto.ResponseSearchField;
import com.flutter.alloffootball.common.domain.Admin;
import com.flutter.alloffootball.common.domain.field.Field;
import com.flutter.alloffootball.common.enums.region.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminRepository {

    Admin findByAccount(String account);

    Page<ResponseSearchField> findAllBySearch(Region region, String word, Pageable pageable);
}
