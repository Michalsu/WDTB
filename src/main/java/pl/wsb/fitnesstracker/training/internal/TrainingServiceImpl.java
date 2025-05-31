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
// TODO: Provide Implementation and correct the return type of the method getTraining
class TrainingServiceImpl implements TrainingProvider, TrainingService {


    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;




    @Override
    public Optional<User> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    /**
     * @return
     */
    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    /**
     * @param activityType
     * @return
     */
    @Override
    public List<TrainingDto> findByActivityType(ActivityType activityType) {
        return trainingRepository.findAllByActivityType(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * @param after
     * @return
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
     * @param userId
     * @return
     */
    @Override
    public List<TrainingDto> findByUserId(Long userId) {
        return trainingRepository.findByUser_Id(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }



    @Override
    @Transactional
    public TrainingDto createTraining(TrainingCreateDto trainingData, User user) {
        Training training = trainingMapper.toEntity(trainingData, user);
        Training saved = trainingRepository.save(training);
        return trainingMapper.toDto(saved);
    }
}
