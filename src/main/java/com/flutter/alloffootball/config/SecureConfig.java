package com.flutter.alloffootball.config;

import com.flutter.alloffootball.enums.Role;
import com.flutter.alloffootball.filter.FlutterAuthorizationFilter;
import com.flutter.alloffootball.filter.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextHolderFilter;

@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@Configuration
@RequiredArgsConstructor
public class SecureConfig {

    private final FlutterAuthorizationFilter flutterAuthorizationFilter;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.addFilterBefore(flutterAuthorizationFilter, SecurityContextHolderFilter.class);
        http.addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        http.authorizeHttpRequests(request ->
            request
                .requestMatchers("/user/**").hasAnyRole(Role.USER.name(), Role.MANAGER.name(), Role.ADMIN.name())
                .requestMatchers("/order/**").hasAnyRole(Role.USER.name(), Role.MANAGER.name(), Role.ADMIN.name())
                .anyRequest().permitAll()
        );

        return http.build();
    }

}