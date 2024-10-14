package com.flutter.alloffootball.admin.controller;

import com.flutter.alloffootball.admin.dto.PageUser;
import com.flutter.alloffootball.admin.dto.user.RequestSearchUser;
import com.flutter.alloffootball.admin.service.AdminService;
import com.flutter.alloffootball.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/user")
public class AdminRestUserController {

    private final AdminService adminService;

    @GetMapping("/get")
    public ResponseEntity<Response> userList(@ModelAttribute RequestSearchUser data) {
        System.out.println("data = " + data);
        Pageable pageable = PageRequest.of(data.getPage() - 1, 10);
        return Response.ok(new PageUser<>(adminService.findAllBySearchUser(data, pageable), data));
    }
}
