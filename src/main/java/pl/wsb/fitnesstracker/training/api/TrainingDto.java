package pl.wsb.fitnesstracker.training.api;

import jakarta.annotation.Nullable;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;
/**
 * Data Transfer Object (DTO) representing a training session.
 * Used to transfer training data between layers, typically from backend to client.
 *
 * @param id             the unique identifier of the training; may be {@code null} if not assigned yet
 * @param user           the user who performed the training
 * @param startTime      the start timestamp of the training session
 * @param endTime        the end timestamp of the training session
 * @param activityType   the type of physical activity performed
 * @param distance       the distance covered during the training
 * @param averageSpeed   the average speed during the training
 */
public record TrainingDto(@Nullable Long id, User user,
                          Date startTime,
                          Date endTime,
                          ActivityType activityType,
                          double distance,
                          double averageSpeed) {
}
