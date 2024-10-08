package com.flutter.alloffootball.admin.controller;

import com.flutter.alloffootball.admin.dto.field.ResponseViewField;
import com.flutter.alloffootball.admin.dto.match.ResponseViewMatch;
import com.flutter.alloffootball.admin.service.AdminService;
import com.flutter.alloffootball.common.enums.MatchStatus;
import com.flutter.alloffootball.common.enums.SexType;
import com.flutter.alloffootball.common.enums.region.Region;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/match")
public class AdminMatchController {

    private final AdminService adminService;

    @GetMapping
    public String match(Model model, Locale locale) {
        model.addAttribute("region", Region.sortedValues(locale));
        model.addAttribute("sex", SexType.values());
        model.addAttribute("state", MatchStatus.values());
        return "admin_match";
    }

    /**
     * 경기 정보 조회
     */
    @GetMapping("/{matchId}")
    public String matchViewPage(@PathVariable("matchId") long matchId, Model model) {
        ResponseViewMatch viewMatch = adminService.findByIdViewMatch(matchId);
        model.addAttribute("match", viewMatch);
        return "admin_match_view";
    }
}
