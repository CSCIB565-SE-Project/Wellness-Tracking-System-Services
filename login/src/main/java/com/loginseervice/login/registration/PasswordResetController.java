package com.loginseervice.login.registration;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.loginseervice.login.user.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/password-reset")
@CrossOrigin(origins = "http://localhost:3000")
public class PasswordResetController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> resetPasswordRequest(@RequestBody EmailRequest emailRequest, HttpServletRequest request) {
        String email = emailRequest.getEmail();
        userService.createPasswordResetTokenForUser(email, applicationUrl(request));
        Map<String, String> response = new HashMap<>(); 
        response.put("message", "If an account with that email exists, we have sent a password reset email.");
        return ResponseEntity.ok(response); 
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    @PostMapping("/confirm")
    public ResponseEntity<?> resetPassword(@RequestBody PasswordResetRequest passwordResetRequest) {
        boolean isReset = userService.resetPassword(passwordResetRequest.getToken(), passwordResetRequest.getNewPassword());
        System.out.println("Received reset password request: " + passwordResetRequest);

        if (isReset) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Password has been reset successfully.");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> errorresponse = new HashMap<>();
            errorresponse.put("message", "Invalid or expired token.");
            return ResponseEntity.badRequest().body(errorresponse); 
        }
    }
}
