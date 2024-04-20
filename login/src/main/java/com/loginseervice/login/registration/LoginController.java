package com.loginseervice.login.registration;

import java.util.Optional;

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
import com.loginseervice.login.user.User;
import com.loginseervice.login.security.JwtService;
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

    @GetMapping("/checkOAuth2")
    public ResponseEntity<?> verifyEmail(@RequestParam("email") String email) {
        try{
            Optional<User> optionalUser = userService.findByEmail(email);

            if (optionalUser.isEmpty()) {
                return ResponseEntity.ok("Registration");
            }
            else{
                User user = optionalUser.get();
                if (user.isOAuth()){
                    return ResponseEntity.ok("Login");
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not an OAuth User");
            }
        } catch(Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
        }
    }
}
