package com.loginseervice.login.registration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    private final UserService userService;
    
    @PostMapping
    public ResponseEntity<?> loginEmployee(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = userService.loginUser(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/oauth2/google")
    public ResponseEntity<String> initiateGoogleOAuth2Flow() {
        // Redirect to Google's OAuth2 authentication page
        String googleAuthUrl = "https://accounts.google.com/o/oauth2/v2/auth"
                + "?client_id=420401997087-mtrv8lq0u4rc2lr9sunuitspc4q32v45.apps.googleusercontent.com"
                + "&redirect_uri=http://localhost:8080/login/oauth2/google/callback"
                + "&response_type=code"
                + "&scope=email profile openid";
        return ResponseEntity.status(HttpStatus.FOUND).body(googleAuthUrl);
    }

    @GetMapping("/oauth2/google/callback")
    public ResponseEntity<String> handleGoogleOAuth2Callback(@RequestParam(name = "code") String code, OAuth2AuthenticationToken authentication) {
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

    @GetMapping("/oauth2/facebook")
    public ResponseEntity<String> initiateFacebookOAuth2Flow() {
        // Redirect to Facebook's OAuth2 authentication page
        String facebookAuthUrl = "https://www.facebook.com/v11.0/dialog/oauth"
                + "?client_id=712691594347760"
                + "&redirect_uri=http://localhost:8080/login/oauth2/facebook/callback"
                + "&scope=email public_profile";
        return ResponseEntity.status(HttpStatus.FOUND).body(facebookAuthUrl);
    }

    @GetMapping("/oauth2/facebook/callback")
    public ResponseEntity<String> handleFacebookOAuth2Callback(@RequestParam(name = "code") String code, OAuth2AuthenticationToken authentication) {
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

