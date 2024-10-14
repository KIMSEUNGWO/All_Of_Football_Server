package com.flutter.alloffootball.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flutter.alloffootball.common.component.JwtUtil;
import com.flutter.alloffootball.common.component.SecurityUtil;
import com.flutter.alloffootball.common.dto.Response;
import com.flutter.alloffootball.common.exception.ErrorCode;
import com.flutter.alloffootball.common.exception.TokenException;
import com.flutter.alloffootball.common.exception.UserError;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final SecurityUtil securityUtil;
    private final ObjectMapper mapper;

    private final List<String> excludePath = List.of(
        "/favicon.ico",
        "/social/login",
        "/register",
        "/css",
        "/js",
        "/font",
        "/social/token",
        "/images/",
        "/admin",
        "/search",
        "/match",
        "/field",
        "/api"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("JwtAuthorizationFilter 시작");

        try {
            checkAccessTokenValid(request);
            filterChain.doFilter(request, response);
        } catch (TokenException e) {
            e.printStackTrace();
            System.out.println("TokenException 발생!! :" + e.getError());
            setErrorResponse(response, e.getError());
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            System.out.println("UsernameNotFoundException 발생!! :" + e.getMessage());
            setErrorResponse(response, UserError.USER_NOT_EXISTS);
        }


    }

    private void setErrorResponse(HttpServletResponse response, ErrorCode responseCode) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Response result = new Response(responseCode);
        try{
            response.getWriter()
                .write(mapper.writeValueAsString(result));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void checkAccessTokenValid(HttpServletRequest request) {
        String accessToken = jwtUtil.extractTokenFromHeader(request);
        jwtUtil.validateAccessToken(accessToken);
        securityUtil.saveUserInSecurityContext(accessToken);
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        if (path.equals("/")) return true;
        // Define paths to exclude
        if (path.equals("/board") || path.matches("^/board/\\d+$")) {
            return true;
        }
        return excludePath.stream().anyMatch(path::startsWith);
    }
}