package com.loginseervice.login.user;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/getdetails")
    public Optional<User> getUserById(@RequestParam("userId") Integer uid){
        return userService.findById(uid);
    }

    @GetMapping("/trainers")
    public List<Trainer> getTrainersBySkill(@RequestParam("skill") String skill){
        return userService.getTrainers(skill);
    }
}
