package com.loginseervice.login.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loginseervice.login.user.User;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
    PasswordResetToken findByUser(User user);
}
