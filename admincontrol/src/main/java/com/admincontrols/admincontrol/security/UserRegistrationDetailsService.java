package com.admincontrols.admincontrol.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.admincontrols.admincontrol.user.User;
import com.admincontrols.admincontrol.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRegistrationDetailsService implements UserDetailsService{
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
