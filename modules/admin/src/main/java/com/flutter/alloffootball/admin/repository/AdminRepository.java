package com.flutter.alloffootball.admin.repository;

import com.flutter.alloffootball.common.domain.Admin;

public interface AdminRepository {

    Admin findByAccount(String account);
}
