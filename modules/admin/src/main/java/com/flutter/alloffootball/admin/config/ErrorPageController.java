package com.flutter.alloffootball.admin.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPageController {

    @RequestMapping("/error-page/400")
    public String errorPage404(HttpServletRequest request, HttpServletResponse response) {
        return "error-page/400";
    }
}
