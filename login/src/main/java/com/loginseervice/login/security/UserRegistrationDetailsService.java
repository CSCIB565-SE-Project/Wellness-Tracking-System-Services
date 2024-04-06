package com.loginseervice.login.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.loginseervice.login.exception.UserIdNotFoundException;
import com.loginseervice.login.user.User;
import com.loginseervice.login.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRegistrationDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).map(UserRegistrationDetails::new)
                                                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    public UserDetails loadUserById(Integer userId) throws UserIdNotFoundException {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserIdNotFoundException("User not found with id: " + userId));
    return new UserRegistrationDetails(user);
    }
}
