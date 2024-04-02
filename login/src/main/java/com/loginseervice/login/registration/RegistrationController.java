package com.loginseervice.login.registration;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loginseervice.login.event.RegistrationCompleteEvent;
import com.loginseervice.login.registration.token.VerificationToken;
import com.loginseervice.login.registration.token.VerificationTokenRepository;
import com.loginseervice.login.user.User;
import com.loginseervice.login.user.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class RegistrationController {

    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository tokenRepository;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegistrationRequest registrationRequest, final HttpServletRequest request) {
        User user = userService.registUser(registrationRequest);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "Success!! Please check your email";
    }

    @GetMapping("/register/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token) {
        VerificationToken verifytoken = tokenRepository.findByToken(token);
        if (verifytoken.getUser().isEnabled()) {
            return "This account has already been verified, please login";
        }
        String verificationResult = userService.validateToken(token);
        if (verificationResult.equalsIgnoreCase("valid")) {
            return "Email Verified Succesfully!! Proceed with Login!";
        }
        return "Invalid Verification Token";
    }

    @PostMapping("/register/oauth2/google")
    public ResponseEntity<String> registerWithGoogle(@RequestBody RegistrationRequest registrationRequest, OAuth2AuthenticationToken authenticationToken, HttpServletRequest request) {
        if (authenticationToken != null && authenticationToken.isAuthenticated()) {
            OAuth2User oauth2User = authenticationToken.getPrincipal();
            String email = oauth2User.getAttribute("email");
            String name = oauth2User.getAttribute("name");

            registrationRequest.setEmail(email);
            registrationRequest.setName(name);

            User user = userService.registUser(registrationRequest);
            publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));

            return ResponseEntity.ok("Registered successfully with Google OAuth2");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Google OAuth2 registration failed");
    }

    @PostMapping("/register/oauth2/facebook")
    public ResponseEntity<String> registerWithFacebook(@RequestBody RegistrationRequest registrationRequest, OAuth2AuthenticationToken authenticationToken, HttpServletRequest request) {
        if (authenticationToken != null && authenticationToken.isAuthenticated()) {
            OAuth2User oauth2User = authenticationToken.getPrincipal();
            String email = oauth2User.getAttribute("email");
            String name = oauth2User.getAttribute("name");

            registrationRequest.setEmail(email);
            registrationRequest.setName(name);

            User user = userService.registUser(registrationRequest);
            publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));

            return ResponseEntity.ok("Registered successfully with Facebook OAuth2");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Facebook OAuth2 registration failed");
    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
