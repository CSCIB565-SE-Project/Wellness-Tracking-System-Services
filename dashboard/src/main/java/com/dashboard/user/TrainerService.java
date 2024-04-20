package com.dashboard.user;

import java.util.Optional;

public class TrainerService {
    
    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public Optional<Trainer> getTrainerById(String trainerId) {
        return trainerRepository.findById(trainerId);
    }

}
