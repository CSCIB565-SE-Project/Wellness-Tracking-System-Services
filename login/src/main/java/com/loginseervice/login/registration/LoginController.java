package com.loginseervice.login.registration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loginseervice.login.user.LoginResponse;
import com.loginseervice.login.user.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    
    private final UserService userService;
    
    @PostMapping
    public ResponseEntity<?> loginEmployee(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = userService.loginUser(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/oauth2/google")
    public ResponseEntity<String> googleOAuth2Callback(OAuth2AuthenticationToken authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            OAuth2User oauth2User = authentication.getPrincipal();
            String email = oauth2User.getAttribute("email");
            String name = oauth2User.getAttribute("name");

            // You can customize the response as needed
            return ResponseEntity.ok("Logged in with Google OAuth2: Email - " + email + ", Name - " + name);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Google OAuth2 authentication failed");
    }

    @GetMapping("/oauth2/facebook")
    public ResponseEntity<String> facebookOAuth2Callback(OAuth2AuthenticationToken authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            OAuth2User oauth2User = authentication.getPrincipal();
            String email = oauth2User.getAttribute("email");
            String name = oauth2User.getAttribute("name");

            // You can customize the response as needed
            return ResponseEntity.ok("Logged in with Facebook OAuth2: Email - " + email + ", Name - " + name);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Facebook OAuth2 authentication failed");
    }
    
    @GetMapping("/login/oauth2/google/callback")
    public ResponseEntity<String> googleOAuth2Callback(@RequestParam(name = "code") String code, OAuth2AuthenticationToken authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            OAuth2User oauth2User = authentication.getPrincipal();
            String email = oauth2User.getAttribute("email");
            String name = oauth2User.getAttribute("name");
            // Proceed with login or registration using OAuth details
            return ResponseEntity.ok("Logged in via Google OAuth with email: " + email);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Google OAuth authentication failed");
        }
    }

    @GetMapping("/login/oauth2/facebook/callback")
    public ResponseEntity<String> facebookOAuth2Callback(@RequestParam(name = "code") String code, OAuth2AuthenticationToken authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            OAuth2User oauth2User = authentication.getPrincipal();
            String email = oauth2User.getAttribute("email");
            String name = oauth2User.getAttribute("name");
            // Proceed with login or registration using OAuth details
            return ResponseEntity.ok("Logged in via Facebook OAuth with email: " + email);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Facebook OAuth authentication failed");
        }
    }
}
