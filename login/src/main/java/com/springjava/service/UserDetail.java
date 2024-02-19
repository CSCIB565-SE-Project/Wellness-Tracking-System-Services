package com.springjava.service;

import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springjava.entity.User;
import com.springjava.repository.UserRepository;

@Service
public class UserDetail implements UserDetailsService{
    @Autowired
    UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserNameOrEmail(username, username);
        if(user == null){
            throw new UsernameNotFoundException("Username do not exists!!");
        }
        GrantedAuthority authorities = user.getRole() != null ? 
                                            new SimpleGrantedAuthority(user.getRole().getRoleName()) : null;
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), 
            authorities != null ? Collections.singleton(authorities) : Collections.emptySet());
    }
}
