package com.loginseervice.login.user;

import java.util.List;
import java.util.Optional;

import com.loginseervice.login.registration.RegistrationRequest;

public interface IUserService {
    List<User> getUsers();
    User registUser(RegistrationRequest request);
    Optional<User> findByEmail(String email);
    void saveUserVerificationToken(User user, String verificationToken);
    String validateToken(String token);
}
