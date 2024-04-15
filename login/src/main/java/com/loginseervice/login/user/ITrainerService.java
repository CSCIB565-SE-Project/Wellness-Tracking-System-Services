package com.loginseervice.login.user;

import java.util.List;

public interface ITrainerService {
    void registerTrainer(User user, List<String> skills);
    List<Trainer> getTrainers(String skillTag);
}
