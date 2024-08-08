package com.flutter.alloffootball.common.config.security;

import com.flutter.alloffootball.common.domain.Admin;
import lombok.Getter;

@Getter
public class AdminUserDetails extends CustomUserDetails {
    
    private final Admin admin;
    public AdminUserDetails(Admin admin) {
        super(admin.getUser());
        this.admin = admin;
    }
}
