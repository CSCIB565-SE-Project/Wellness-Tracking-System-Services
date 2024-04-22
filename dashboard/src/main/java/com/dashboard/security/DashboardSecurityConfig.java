package com.dashboard.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.Filter;

@Configuration
@EnableWebSecurity
public class DashboardSecurityConfig {
    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, Filter jwtAuthFilter) throws Exception {
        http
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .cors(Customizer.withDefaults()).csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(requests -> requests
                .requestMatchers(request -> {
                    String path = request.getServletPath();
                    return path.startsWith("/timetables/") ||
                           path.startsWith("/progress-metrics/") ||
                           path.startsWith("/appointments/");
                }).permitAll() // Allow access based on request matchers
                .anyRequest().authenticated() // All other requests require authentication
            )
            .sessionManagement(session -> session
                .sessionFixation().migrateSession() // Ensure session fixation protection
                .invalidSessionUrl("/login") // Redirect to login page for invalid sessions
                .maximumSessions(1).expiredUrl("/login?expired") // Allow only one session per user
            )
            .formLogin(form -> form
                .loginPage("http://localhost:3000") // Specify custom login page URL here
                .permitAll()
            );
        
        return http.build();
    }
}
