package com.flutter.alloffootball.admin.controller;

import com.flutter.alloffootball.admin.dto.RequestSaveFieldForm;
import com.flutter.alloffootball.admin.dto.ResponseViewField;
import com.flutter.alloffootball.admin.dto.field.ResponseEditField;
import com.flutter.alloffootball.admin.service.AdminService;
import com.flutter.alloffootball.common.config.security.AdminUserDetails;
import com.flutter.alloffootball.common.enums.region.Region;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 구장 정보 조회
     */
    @GetMapping("/{fieldId}")
    public String fieldView(@PathVariable("fieldId") long fieldId, Model model) {
        ResponseViewField viewField = adminService.findByIdViewField(fieldId);
        model.addAttribute("field", viewField);
        return "admin_field_view";
    }

    /**
     * GET 구장 등록 데이터
     */
    @GetMapping("/add")
    public String getFieldAdd(Model model) {
        model.addAttribute("region", Region.values());
        model.addAttribute("saveFieldForm", new RequestSaveFieldForm());
        return "admin_field_add";
    }

    /**
     * POST 구장 등록
     */
    @PostMapping("/add")
    public String postFieldAdd(@Validated @ModelAttribute("saveFieldForm") RequestSaveFieldForm saveFieldForm,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "admin_field_add";

        adminService.saveField(saveFieldForm);
        return "redirect:/admin/field";
    }

    @GetMapping("/{fieldId}/edit")
    public String fieldEdit(@PathVariable("fieldId") Long fieldId, Model model) {
        ResponseEditField form = adminService.getEditFieldForm(fieldId);

        model.addAttribute("regions", Region.values());
        model.addAttribute("fieldId", fieldId);
        model.addAttribute("editField", form);
        return "admin_field_edit";
    }




}
