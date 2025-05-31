package pl.wsb.fitnesstracker.training.internal;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingCreateDto;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.training.api.TrainingNotFoundException;
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

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto createTraining(@RequestBody TrainingCreateDto trainingDto) {
        User user = userRepository.findById(trainingDto.userId())
                .orElseThrow(() -> new TrainingNotFoundException(trainingDto.userId()));
        return trainingService.createTraining(trainingDto, user);
    }



    @GetMapping("/activityType")
    public List<TrainingDto> getAllByActivityType(@RequestParam ActivityType activityType) {
        return trainingService.findByActivityType(activityType);
    }

    @GetMapping("/finished/{afterTime}")
    public List<TrainingDto> getFinishedAfter(@PathVariable LocalDate afterTime) {
        return trainingService.findFinishedAfter(afterTime);
    }

    @GetMapping("/{userId}")
    public List<TrainingDto> getAllByUser(@PathVariable Long userId) {
        return trainingService.findByUserId(userId);
    }
}
