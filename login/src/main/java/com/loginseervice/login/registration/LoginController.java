package com.loginseervice.login.registration;

import org.springframework.web.bind.annotation.RestController;

import com.loginseervice.login.user.LoginResponse;
import com.loginseervice.login.user.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
//so frontend can access in local testing
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
    private final UserService userService;
    @PostMapping
    public  ResponseEntity<?> loginEmployee(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = userService.loginUser(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
    
    // @GetMapping
    // public String login() {
    //     return "login";
    // }
    
}
