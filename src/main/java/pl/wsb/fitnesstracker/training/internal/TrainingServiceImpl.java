package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.training.api.TrainingService;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
// TODO: Provide Implementation and correct the return type of the method getTraining
class TrainingServiceImpl implements TrainingProvider, TrainingService {


    private final TrainingRepository trainingRepository;




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
     * @param training
     * @return
     */
    @Override
    public Training createTraining(Training training) {
       log.info("Creating training {}", training);
       if (training.getId() != null) {
           throw new IllegalArgumentException("Training id is null");
       }
       return trainingRepository.save(training);
    }
}
