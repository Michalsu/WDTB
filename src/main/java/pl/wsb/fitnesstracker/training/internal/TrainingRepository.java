package pl.wsb.fitnesstracker.training.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.training.api.Training;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

interface TrainingRepository extends JpaRepository<Training, Long> {
    /**
     * Finds a training by its ID.
     * This default implementation streams all trainings and filters by ID.
     *
     * @param id the ID of the training to find
     * @return an {@link Optional} containing the found training or empty if not found
     */
    default Optional<Training> findById(Long id) {
        return findAll().stream()
                .filter(training -> Objects.equals(training.getId(), id))
                .findAny();
    }
    /**
     * Finds all trainings of a specific activity type.
     *
     * @param activityType the activity type to filter by
     * @return list of trainings matching the given activity type
     */
    List<Training> findAllByActivityType(ActivityType activityType);
    /**
     * Finds all trainings that finished after the specified date.
     *
     * @param endTime the date after which trainings finished
     * @return list of trainings finished after the specified date
     */
    List<Training> findAllByEndTimeAfter(Date endTime);
    /**
     * Finds all trainings associated with a specific user ID.
     *
     * @param userId the ID of the user
     * @return list of trainings linked to the specified user
     */
    List<Training> findByUser_Id(Long userId);
}
