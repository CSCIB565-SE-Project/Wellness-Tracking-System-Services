package com.search.search;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.search.user.Trainer;
import com.search.user.UserService;

@RestController
@RequestMapping("/professionals")
@CrossOrigin(origins = "http://localhost:3000")
public class ProfessionalController {

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public List<Trainer> searchProfessionals(@RequestParam String specialty) { 
        return userService.searchProfessionals(specialty);
    }
}
