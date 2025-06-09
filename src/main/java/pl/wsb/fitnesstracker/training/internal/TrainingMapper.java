package pl.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingCreateDto;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.user.api.User;

@Component
class TrainingMapper {
    /**
     * Converts a {@link Training} entity to its DTO representation.
     *
     * @param training the training entity to convert
     * @return a {@link TrainingDto} containing data from the training entity
     */
    TrainingDto toDto(Training training) {
        return new TrainingDto(training.getId(),
                training.getUser(),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed());
    }
    /**
     * Converts a {@link TrainingDto} to a {@link Training} entity.
     * The {@link User} must be provided explicitly as it is not fully contained in the DTO.
     *
     * @param trainingDto the DTO containing training data
     * @param user        the user associated with the training
     * @return a {@link Training} entity populated with data from the DTO and user
     */
    Training toEntity(TrainingDto trainingDto, User user) {
        return new Training(
                user,
                trainingDto.startTime(),
                trainingDto.endTime(),
                trainingDto.activityType(),
                trainingDto.distance(),
                trainingDto.averageSpeed()
        );
    }
    /**
     * Converts a {@link TrainingCreateDto} to a new {@link Training} entity.
     * The {@link User} must be provided explicitly.
     *
     * @param training the DTO containing data needed to create a training
     * @param user     the user associated with the training
     * @return a new {@link Training} entity based on the create DTO and user
     */
    public Training toEntity(TrainingCreateDto training, User user) {
        return new Training(user,
                training.startTime(),
                training.endTime(),
                training.activityType(),
                training.distance(),
                training.averageSpeed()
        );
    }
}
