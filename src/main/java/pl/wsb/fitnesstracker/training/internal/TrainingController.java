package pl.wsb.fitnesstracker.training.internal;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.training.api.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.internal.UserRepository;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {
    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;
    private final UserRepository userRepository;
    /**
     * Retrieves all training sessions.
     *
     * @return list of all trainings as {@link TrainingDto}
     */
    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }
    /**
     * Creates a new training session.
     *
     * @param trainingDto DTO containing the details of the training to be created
     * @return the created training as {@link TrainingDto}
     * @throws TrainingNotFoundException if the user specified by userId in the DTO does not exist
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto createTraining(@RequestBody TrainingCreateDto trainingDto) {
        User user = userRepository.findById(trainingDto.userId())
                .orElseThrow(() -> new TrainingNotFoundException(trainingDto.userId()));
        return trainingService.createTraining(trainingDto, user);
    }


    /**
     * Retrieves all trainings filtered by activity type.
     *
     * @param activityType the activity type to filter trainings by
     * @return list of trainings matching the specified activity type
     */
    @GetMapping("/activityType")
    public List<TrainingDto> getAllByActivityType(@RequestParam ActivityType activityType) {
        return trainingService.findByActivityType(activityType);
    }
    /**
     * Retrieves all trainings that finished after the specified date.
     *
     * @param afterTime the date after which trainings must have finished
     * @return list of trainings finished after the specified date
     */
    @GetMapping("/finished/{afterTime}")
    public List<TrainingDto> getFinishedAfter(@PathVariable LocalDate afterTime) {
        return trainingService.findFinishedAfter(afterTime);
    }
    /**
     * Retrieves all trainings for the specified user.
     *
     * @param userId the ID of the user whose trainings should be retrieved
     * @return list of trainings belonging to the specified user
     */
    @GetMapping("/{userId}")
    public List<TrainingDto> getAllByUser(@PathVariable Long userId) {
        return trainingService.findByUserId(userId);
    }
    /**
     * Updates an existing training session.
     *
     * @param trainingId the ID of the training to update
     * @param training the updated training data as {@link TrainingUpdateDto}
     * @return the updated training as {@link TrainingDto}
     * @throws TrainingNotFoundException if the user specified by userId in the DTO does not exist
     */
    @PutMapping("/{trainingId}")
    public TrainingDto updateTraining(@PathVariable Long trainingId, @RequestBody TrainingUpdateDto training) {
        User user = userRepository.findById(training.userId())
                .orElseThrow(() -> new TrainingNotFoundException(training.userId()));
        return trainingService.update(trainingId, training, user);
    }
}
