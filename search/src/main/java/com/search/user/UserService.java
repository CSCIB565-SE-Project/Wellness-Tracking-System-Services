package com.search.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private TrainerRepository trainerRepository;

    public List<Trainer> searchProfessionals(
            String userId,
            String speciality,
            String gender,
            String location) {
        return trainerRepository.findByUserIdContainingIgnoreCaseOrSpecialityContainingIgnoreCaseOrGenderContainingIgnoreCaseOrLocationContainingIgnoreCase(userId, speciality, gender, location);
    }   
}
