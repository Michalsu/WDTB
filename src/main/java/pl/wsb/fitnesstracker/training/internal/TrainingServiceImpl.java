package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wsb.fitnesstracker.training.api.*;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j

class TrainingServiceImpl implements TrainingProvider, TrainingService {


    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;



    /**
     * Retrieves a training entity by its unique identifier.
     *
     * @param trainingId the ID of the training to retrieve
     * @return an {@link Optional} containing the {@link Training} if found,
     *         or {@link Optional#empty()} if no training with the given ID exists
     */
    @Override
    public Optional<Training> getTraining(final Long trainingId) {return trainingRepository.findById(trainingId);}

    /**
     * Retrieves all trainings stored in the system.
     *
     * @return a list of all {@link Training} entities
     */
    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    /**
     * Finds all trainings filtered by the specified activity type.
     *
     * @param activityType the activity type to filter trainings by
     * @return list of {@link TrainingDto} matching the activity type
     */
    @Override
    public List<TrainingDto> findByActivityType(ActivityType activityType) {
        return trainingRepository.findAllByActivityType(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Finds all trainings that finished after a given date.
     *
     * @param after the date after which trainings should have finished
     * @return list of {@link TrainingDto} for trainings finished after the given date
     */
    @Override
    public List<TrainingDto> findFinishedAfter(LocalDate after) {
        ZoneId zoneId = ZoneId.systemDefault(); // or choose the appropriate one
        Instant instant = after.atStartOfDay(zoneId).toInstant();
        Date afterDate = Date.from(instant);
        return trainingRepository.findAllByEndTimeAfter(afterDate)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Finds all trainings associated with a given user ID.
     *
     * @param userId the ID of the user whose trainings are requested
     * @return list of {@link TrainingDto} linked to the user
     */
    @Override
    public List<TrainingDto> findByUserId(Long userId) {
        return trainingRepository.findByUser_Id(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }
    /**
     * Creates a new training record.
     *
     * @param trainingData the data required to create a training
     * @param user         the user associated with the training
     * @return the created {@link TrainingDto}
     */
    @Override
    @Transactional
    public TrainingDto createTraining(TrainingCreateDto trainingData, User user) {
        Training training = trainingMapper.toEntity(trainingData, user);
        Training saved = trainingRepository.save(training);
        return trainingMapper.toDto(saved);
    }
    /**
     * Updates an existing training identified by its ID.
     *
     * @param trainingId the ID of the training to update
     * @param training   the updated training data
     * @param user       the user associated with the training
     * @return the updated {@link TrainingDto}
     * @throws TrainingNotFoundException if training with the given ID does not exist
     */
    @Override
    @Transactional
    public TrainingDto update(Long trainingId, TrainingUpdateDto training, User user) {
        Training existing = trainingRepository.findById(trainingId).orElseThrow(() -> new TrainingNotFoundException(trainingId));
        existing.setUser(user);
        existing.setStartTime(training.startTime());
        existing.setEndTime(training.endTime());
        existing.setActivityType(training.activityType());
        existing.setDistance(training.distance());
        existing.setAverageSpeed(training.averageSpeed());
        Training updated = trainingRepository.save(existing);

        return trainingMapper.toDto(updated);
    }

}
