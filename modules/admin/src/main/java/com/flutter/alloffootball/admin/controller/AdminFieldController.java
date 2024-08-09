package com.flutter.alloffootball.admin.controller;

import com.flutter.alloffootball.admin.dto.RequestSaveFieldForm;
import com.flutter.alloffootball.admin.service.AdminService;
import com.flutter.alloffootball.common.config.security.AdminUserDetails;
import com.flutter.alloffootball.common.enums.region.Region;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/add")
    public String getFieldAdd(Model model) {
        model.addAttribute("region", Region.values());
        model.addAttribute("saveFieldForm", new RequestSaveFieldForm());
        return "admin_field_add";
    }
    @PostMapping("/add")
    public String postFieldAdd(@ModelAttribute @Validated RequestSaveFieldForm saveFieldForm,
                               BindingResult bindingResult) {
        for (int i = 0; i < 26; i++) {

            adminService.saveField(saveFieldForm);
        }
        return "redirect:/admin/field";
    }

}
