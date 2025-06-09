package pl.wsb.fitnesstracker.training.api;

import jakarta.annotation.Nullable;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;
/**
 * Data Transfer Object (DTO) used for creating a new {@link pl.wsb.fitnesstracker.training.api.Training} entity.
 * Contains all necessary information required to register a training session for a user.
 *
 * This DTO may be used in API requests to encapsulate training creation input.
 *
 * @param userId        the ID of the user to whom the training belongs
 * @param user          the full {@link User} object (optional; may be used for internal processing or mapping)
 * @param startTime     the timestamp marking the beginning of the training
 * @param endTime       the timestamp marking the end of the training
 * @param activityType  the type of activity performed during the training (e.g., RUNNING, CYCLING)
 * @param distance      the total distance covered during the training
 * @param averageSpeed  the average speed during the training
 */
public record TrainingCreateDto(Long userId, User user,
                             Date startTime,
                             Date endTime,
                             ActivityType activityType,
                             double distance,
                             double averageSpeed) {
}
