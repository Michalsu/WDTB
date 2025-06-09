package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.user.api.User;

public interface TrainingService {
    /**
     * Creates a new training session for a given user based on provided data.
     *
     * @param trainingData the data needed to create the training
     * @param user         the user who owns the training
     * @return the created training represented as a {@link TrainingDto}
     */
    TrainingDto createTraining(TrainingCreateDto trainingData, User user);
    /**
     * Updates an existing training session identified by {@code trainingId}
     * with new data provided in {@code body}, for the specified user.
     *
     * @param trainingId the ID of the training to update
     * @param body       the new training data
     * @param user       the user who owns the training
     * @return the updated training represented as a {@link TrainingDto}
     */
    TrainingDto update(Long trainingId, TrainingUpdateDto body, User user);
}
