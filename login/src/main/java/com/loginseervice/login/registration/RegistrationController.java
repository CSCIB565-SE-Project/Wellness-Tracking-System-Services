package com.loginseervice.login.registration;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loginseervice.login.event.RegistrationCompleteEvent;
import com.loginseervice.login.registration.token.VerificationToken;
import com.loginseervice.login.registration.token.VerificationTokenRepository;
import com.loginseervice.login.user.User;
import com.loginseervice.login.user.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository tokenRepository;
    @PostMapping
    public String registerUser(@RequestBody RegistrationRequest registrationRequest, final HttpServletRequest request){
        User user = userService.registUser(registrationRequest);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "Success!! Please check your email";
    }

    @GetMapping("/verifyEmail")    
    public String verifyEmail(@RequestParam("token") String token){
        VerificationToken verifytoken = tokenRepository.findByToken(token);
        if(verifytoken.getUser().isEnabled()){
            return "This account has already been verified, please login";
        }
        String verificationResult = userService.validateToken(token);
        if(verificationResult.equalsIgnoreCase("valid")){
            return "Email Verified Succesfully!!Proceed with Login!";
        }
        return "Invalid Verification Token";
    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }
}

