package com.dashboard.user;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MealplanService {

    private final MealplanRepository mealplanRepository;

    public List<Mealplan> getMealplanByUserId(Integer userId) {
        return mealplanRepository.findByUserId(userId);
    }

    public Mealplan createMealplan(Mealplan mealplan) {
        return mealplanRepository.save(mealplan);
    }



}
