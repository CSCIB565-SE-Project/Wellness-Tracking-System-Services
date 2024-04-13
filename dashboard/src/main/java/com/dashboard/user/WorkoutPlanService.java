package com.dashboard.user;

import java.util.List;
import java.util.Optional;

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

    public Optional<WorkoutPlan> getWorkoutPlanById(Long id) {
        return workoutPlanRepository.findById(id);
    }

    public WorkoutPlan createWorkoutPlan(WorkoutPlan workoutPlan) {
        return workoutPlanRepository.save(workoutPlan);
    }

    public WorkoutPlan updateWorkoutPlan(Long id, WorkoutPlan updatedWorkoutPlan) {
        // Check if the workout plan exists
        if (!workoutPlanRepository.existsById(id)) {
            throw new NotFoundException("Workout plan not found");
        }
        updatedWorkoutPlan.setId(id); // Ensure the ID is set
        return workoutPlanRepository.save(updatedWorkoutPlan);
    }

    public void deleteWorkoutPlan(Long id) {
        // Check if the workout plan exists
        if (!workoutPlanRepository.existsById(id)) {
            throw new NotFoundException("Workout plan not found");
        }
        workoutPlanRepository.deleteById(id);
    }
}

