package com.flutter.alloffootball.admin.controller;

import com.flutter.alloffootball.common.config.security.AdminUserDetails;
import com.flutter.alloffootball.common.enums.region.Region;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/field")
public class AdminFieldController {

    @GetMapping
    public String ground(Model model,
                         @AuthenticationPrincipal AdminUserDetails userDetails) {
        model.addAttribute("name", userDetails.getAdmin().getName());
        model.addAttribute("authority", userDetails.getAdmin().getAuthority().getKo());
        model.addAttribute("region", Region.values());
        return "admin_field";
    }
}
