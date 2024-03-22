package com.loginseervice.login.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class UserRegistrationSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
        .cors(Customizer.withDefaults()).csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests.requestMatchers("/register/**").permitAll())
                .authorizeHttpRequests(requests -> requests.requestMatchers("/login/**").permitAll())
                .authorizeHttpRequests(requests -> requests.requestMatchers("/users/**")
                .hasAnyAuthority("USER", "ADMIN")
                )
        .sessionManagement(session -> session
            .sessionFixation().migrateSession() // Ensure session fixation protection
            .invalidSessionUrl("/login") // Redirect to login page for invalid sessions
            .maximumSessions(1).expiredUrl("/login?expired") // Allow only one session per user
        )
        .formLogin(form -> form
            .loginPage("/login") // Specify custom login page URL here
            .permitAll()
        )
        .build();
    }
}
