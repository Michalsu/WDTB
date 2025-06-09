package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;
/**
 * Data Transfer Object (DTO) used to update an existing training session.
 * Contains new values for the training's properties.
 *
 * @param userId        the ID of the user who owns the training
 * @param user          the full {@link User} object (optional; may be used internally)
 * @param startTime     the updated start time of the training
 * @param endTime       the updated end time of the training
 * @param activityType  the updated activity type
 * @param distance      the updated distance covered in kilometers
 * @param averageSpeed  the updated average speed in kilometers per hour
 */
public record TrainingUpdateDto(Long userId, User user,
                                Date startTime,
                                Date endTime,
                                ActivityType activityType,
                                double distance,
                                double averageSpeed) {
}