package com.loginseervice.login.user;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.loginseervice.login.exception.UserAlreadyExistsException;
import com.loginseervice.login.registration.LoginRequest;
import com.loginseervice.login.registration.RegistrationRequest;
import com.loginseervice.login.registration.token.PasswordResetToken;
import com.loginseervice.login.registration.token.PasswordResetTokenRepository;
import com.loginseervice.login.registration.token.VerificationToken;
import com.loginseervice.login.registration.token.VerificationTokenRepository;
import com.loginseervice.login.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final VerificationTokenRepository tokenRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registUser(RegistrationRequest request) {
        Optional<User> user = this.findByEmail(request.email());
        if (user.isPresent()) {
            throw new UserAlreadyExistsException("User with Email " + request.email() + " already exists!!");
        }
        var newUser = new User();
        newUser.setFname(request.fname());
        newUser.setMname(request.mname());
        newUser.setLname(request.lname());
        newUser.setDob(request.dob());
        newUser.setGender(request.gender());
        newUser.setEmail(request.email());
        newUser.setUsername(request.username());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setRole(request.role());
        return userRepository.save(newUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public String getPasswordEmail(Optional<User> user) {
        User usr = user.get();
        String password = usr.getPassword();
        return password;
    }

    @Override
    public void saveUserVerificationToken(User user, String token) {
        var verificationToken = new VerificationToken(token, user);
        tokenRepository.save(verificationToken);
    }

    @Override
    public String validateToken(String token) {
        VerificationToken theToken = tokenRepository.findByToken(token);
        if (theToken == null) {
            return "Invalid Verification Token";
        }
        User user = theToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if (theToken.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
            tokenRepository.delete(theToken);
            return "Token Already Expired!";
        }
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }

    @Override
    public LoginResponse loginUser(LoginRequest request) {
        String statusMessage = "";
        User usr = null;
        Boolean status = false;
        Optional<User> user = this.findByEmail(request.email());
        if (user.isPresent()) {
            if (user.get().isEnabled()) {
                String password = request.password();
                String encodedPassword = this.getPasswordEmail(user);
                Boolean isPwdMatch = passwordEncoder.matches(password, encodedPassword);
                if (isPwdMatch) {
                    usr = user.get();
                    status = true;
                    statusMessage = "Login Success";
                } else {
                    statusMessage = "Password Mismatch";
                }
            } else {
                statusMessage = "User yet to be verified!";
            }
        } else {
            statusMessage = "User does not exist";
        }
        if (status) {
            String token = jwtService.GenerateToken(usr.getUsername());
            return new LoginResponse(statusMessage, status, usr, token);
        } else {
            // Return LoginResponse with null user and token
            return new LoginResponse(statusMessage, status, null, null);
        }
    }
    

    @Override
    public void createPasswordResetTokenForUser(String email, String appUrl) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String token = UUID.randomUUID().toString();

        PasswordResetToken existingToken = passwordResetTokenRepository.findByUser(user);
        if (existingToken != null) {
            existingToken.setToken(token);
            existingToken.setExpiryDate(LocalDateTime.now().plusHours(1));
            passwordResetTokenRepository.save(existingToken);
        } else {
            PasswordResetToken resetToken = new PasswordResetToken();
            resetToken.setToken(token);
            resetToken.setUser(user);
            resetToken.setExpiryDate(LocalDateTime.now().plusHours(1));
            passwordResetTokenRepository.save(resetToken);
        }

        sendPasswordResetEmail(user, appUrl, token);
    }

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendPasswordResetEmail(User user, String appUrl, String token) {
        String url = "http://localhost:3000/reset-password?token=" + token;
        String subject = "Password Reset Request";
        String content = "To reset your password, click the link below:\n" + url;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject(subject);
        email.setText(content);
        mailSender.send(email);
    }

    @Override
    public boolean resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token);
        if (resetToken == null || resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return false;
        }
        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        passwordResetTokenRepository.delete(resetToken);
        return true;
    }
}
