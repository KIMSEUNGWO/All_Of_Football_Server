package com.flutter.alloffootball.admin.controller;

import com.flutter.alloffootball.admin.dto.RequestSearchField;
import com.flutter.alloffootball.admin.service.AdminService;
import com.flutter.alloffootball.common.config.security.AdminUserDetails;
import com.flutter.alloffootball.admin.dto.PageField;
import com.flutter.alloffootball.common.dto.Response;
import com.flutter.alloffootball.common.enums.region.Region;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/field")
public class AdminRestFieldController {

    private final AdminService adminService;

    @GetMapping("/get")
    public ResponseEntity<Response> fieldList(@ModelAttribute RequestSearchField data) {
        Pageable pageable = PageRequest.of(data.getPage() - 1, 10);
        return Response.ok(new PageField<>(adminService.findAllBySearchField(data, pageable), data));
    }
}
