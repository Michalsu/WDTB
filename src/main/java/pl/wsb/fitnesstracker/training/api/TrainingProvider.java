package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TrainingProvider {

    /**
     * Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<Training> getTraining(Long trainingId);

    /**
     * Retrieves all training sessions available.
     *
     * @return list of all trainings
     */
    List<Training> getAllTrainings();
    /**
     * Finds all trainings matching the specified activity type.
     *
     * @param activityType the type of activity to filter trainings by
     * @return list of trainings matching the activity type
     */
    List<TrainingDto> findByActivityType(ActivityType activityType);
    /**
     * Finds all trainings that finished after the specified date.
     *
     * @param afterTime the date after which trainings must have finished
     * @return list of trainings finished after the specified date
     */
    List<TrainingDto> findFinishedAfter(LocalDate afterTime);
    /**
     * Finds all trainings associated with the specified user ID.
     *
     * @param userId the ID of the user whose trainings should be retrieved
     * @return list of trainings belonging to the user
     */
    List<TrainingDto> findByUserId(Long userId);
}
