package com.search.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.search.user.Trainer;
import com.search.user.UserService;

@RestController
@RequestMapping("/professionals")
public class ProfessionalController {

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public List<Trainer> searchProfessionals(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String specialty,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String location) {
        return userService.searchProfessionals(userId, firstName, username, specialty, gender, location);
    }
}
