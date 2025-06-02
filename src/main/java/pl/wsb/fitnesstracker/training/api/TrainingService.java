package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.user.api.User;

public interface TrainingService {

    TrainingDto createTraining(TrainingCreateDto trainingData, User user);

    TrainingDto update(Long trainingId, TrainingUpdateDto body, User user);
}
