package com.loginseervice.login.user;

import java.util.List;
import java.util.Optional;

import com.loginseervice.login.registration.LoginRequest;
import com.loginseervice.login.registration.RegistrationRequest;

public interface IUserService {
    List<User> getUsers();
    User registUser(RegistrationRequest request);
    Optional<User> findByEmail(String email);
    String getPasswordEmail(Optional<User> user);
    void saveUserVerificationToken(User user, String verificationToken);
    String validateToken(String token);
    LoginResponse loginUser(LoginRequest request);
    void createPasswordResetTokenForUser(String email, String appUrl);
    void sendPasswordResetEmail(User user, String appUrl, String token);
}
