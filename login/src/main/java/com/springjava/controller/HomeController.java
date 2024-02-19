package com.springjava.controller;

import org.springframework.web.bind.annotation.RestController;

import com.springjava.dto.LoginDto;
import com.springjava.dto.SignUpDto;
import com.springjava.entity.Role;
import com.springjava.entity.User;
import com.springjava.repository.RoleRepository;
import com.springjava.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api")
public class HomeController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new
                                        UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User login succesfully!...", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username already exists!!", HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email already exists!!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setFname(signUpDto.getFname());
        user.setMname(signUpDto.getMname());
        user.setLname(signUpDto.getLname());
        user.setGender(signUpDto.getGender());
        user.setDoB(signUpDto.getDoB());
        user.setEmail(signUpDto.getEmail());
        user.setUsername(signUpDto.getUsername());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRole(role);
        userRepository.save(user);
        return new ResponseEntity<>("Registered Succesfully!!", HttpStatus.OK);
    }

    
    
}
