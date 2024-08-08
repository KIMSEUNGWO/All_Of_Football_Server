package com.flutter.alloffootball.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flutter.alloffootball.component.JwtUtil;
import com.flutter.alloffootball.component.SecurityUtil;
import com.flutter.alloffootball.dto.Response;
import com.flutter.alloffootball.exception.TokenError;
import com.flutter.alloffootball.exception.TokenException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final SecurityUtil securityUtil;
    private final ObjectMapper mapper = new ObjectMapper();

    private final List<String> excludePath = List.of(
        "/favicon.ico",
        "/social/login",
        "/register",
        "/social/token",
        "/images/"
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
        }


    }

    private void setErrorResponse(HttpServletResponse response, TokenError responseCode) {
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
        return excludePath.stream().anyMatch(path::startsWith);
    }
}