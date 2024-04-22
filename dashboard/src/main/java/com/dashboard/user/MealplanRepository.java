package com.dashboard.user;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MealplanRepository extends MongoRepository<Mealplan, String> {    
    List<Mealplan> findByUserId(Integer userId);
}
