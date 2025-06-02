package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;

public record TrainingUpdateDto(Long userId, User user,
                                Date startTime,
                                Date endTime,
                                ActivityType activityType,
                                double distance,
                                double averageSpeed) {
}