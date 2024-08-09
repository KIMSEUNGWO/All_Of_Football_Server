package com.flutter.alloffootball.admin.controller;

import com.flutter.alloffootball.admin.dto.ResponseSearchField;
import com.flutter.alloffootball.admin.service.AdminService;
import com.flutter.alloffootball.common.config.security.AdminUserDetails;
import com.flutter.alloffootball.common.dto.PageDto;
import com.flutter.alloffootball.common.dto.Response;
import com.flutter.alloffootball.common.enums.region.Region;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/field")
public class AdminFieldController {

    private final AdminService adminService;

    @GetMapping
    public String field(Model model,
                         @AuthenticationPrincipal AdminUserDetails userDetails) {
//        model.addAttribute("name", userDetails.getAdmin().getName());
//        model.addAttribute("authority", userDetails.getAdmin().getAuthority().getKo());
        model.addAttribute("region", Region.values());
        return "admin_field";
    }

    @ResponseBody
    @GetMapping("/get")
    public ResponseEntity<Response> fieldList(@RequestParam(value = "region", required = false) Region region,
                                                @RequestParam(value = "word", required = false, defaultValue = "") String word,
                                                @RequestParam("page") int page, @AuthenticationPrincipal AdminUserDetails userDetails) {
        Pageable pageable = PageRequest.of(page, 10);
        PageDto<ResponseSearchField> searchFieldPage = new PageDto<>(adminService.findAllBySearch(region, word, pageable));
        System.out.println("searchFieldPage = " + searchFieldPage);
        return Response.ok(searchFieldPage);
    }
}
