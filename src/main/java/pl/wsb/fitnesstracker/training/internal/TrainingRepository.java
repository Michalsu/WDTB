package pl.wsb.fitnesstracker.training.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.training.api.Training;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

interface TrainingRepository extends JpaRepository<Training, Long> {

    default Optional<Training> findById(Long id) {
        return findAll().stream()
                .filter(training -> Objects.equals(training.getId(), id))
                .findAny();
    }

    List<Training> findAllByActivityType(ActivityType activityType);

    List<Training> findAllByEndTimeAfter(Date endTime);

    List<Training> findByUser_Id(Long userId);
}
