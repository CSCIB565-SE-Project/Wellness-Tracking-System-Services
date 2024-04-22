package com.dashboard.dashboard;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.user.Mealplan;
import com.dashboard.user.MealplanService;

@RestController
@RequestMapping("/mealplan")
@CrossOrigin(origins = "http://localhost:3000")
public class MealplanController {

    private final MealplanService mealplanService;

    public MealplanController(MealplanService mealplanService) {
        this.mealplanService = mealplanService;
    }

    @GetMapping("/getByUser")
    public ResponseEntity<List<Mealplan>> getMealplanByUserId(@RequestParam Integer userId) {
        List<Mealplan> mealplans = mealplanService.getMealplanByUserId(userId);
        return ResponseEntity.ok(mealplans);
    }

    @PostMapping("/create")
    public ResponseEntity<Mealplan> createMealplan(@RequestBody Mealplan mealplan) {
        Mealplan createdMealplan = mealplanService.createMealplan(mealplan);
        return new ResponseEntity<>(createdMealplan, HttpStatus.CREATED);
    }
}
