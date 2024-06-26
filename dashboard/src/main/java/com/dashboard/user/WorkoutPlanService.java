package com.dashboard.user;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashboard.exception.NotFoundException;

@Service
public class WorkoutPlanService {

    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

    public List<WorkoutPlan> getAllWorkoutPlans() {
        return workoutPlanRepository.findAll();
    }

    public Optional<WorkoutPlan> getWorkoutPlanById(ObjectId id) {
        return workoutPlanRepository.findById(id);
    }

    public WorkoutPlan createWorkoutPlan(WorkoutPlan workoutPlan) {
        return workoutPlanRepository.save(workoutPlan);
    }

    public void deleteWorkoutPlan(ObjectId id) {
        // Check if the workout plan exists
        if (!workoutPlanRepository.existsById(id)) {
            throw new NotFoundException("Workout plan not found");
        }
        workoutPlanRepository.deleteById(id);
    }
}

