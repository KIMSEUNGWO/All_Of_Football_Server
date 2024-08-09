package com.flutter.alloffootball.admin.controller;

import com.flutter.alloffootball.admin.service.AdminService;
import com.flutter.alloffootball.common.config.security.AdminUserDetails;
import com.flutter.alloffootball.common.dto.PageDto;
import com.flutter.alloffootball.common.dto.Response;
import com.flutter.alloffootball.common.enums.region.Region;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/field")
public class AdminRestFieldController {

    private final AdminService adminService;

    @GetMapping("/get")
    public ResponseEntity<Response> fieldList(@RequestParam(value = "region", required = false) Region region,
                                              @RequestParam(value = "word", required = false, defaultValue = "") String word,
                                              @RequestParam(value = "page",defaultValue = "1") int page, @AuthenticationPrincipal AdminUserDetails userDetails) {
        Pageable pageable = PageRequest.of(page - 1, 10);
        return Response.ok(new PageDto<>(adminService.findAllBySearchField(region, word, pageable), word, region));
    }
}
