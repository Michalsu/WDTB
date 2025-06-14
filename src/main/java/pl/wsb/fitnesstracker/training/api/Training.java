package pl.wsb.fitnesstracker.training.api;

import jakarta.persistence.*;
import lombok.*;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;
/**
 * Represents a single user training session in the FitnessTracker system.
 * Contains information about the time span, type of activity, distance covered,
 * and average speed during the session.
 *
 * This class is mapped as a JPA entity to the "trainings" table.
 */
@Entity
@Table(name = "trainings")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Training {
    /**
     * Unique identifier of the training.
     * Automatically generated by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * The user to whom this training belongs.
     * Defined as a many-to-one relationship with the {@link User} entity.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    /**
     * The start time of the training session.
     * Must not be {@code null}.
     */
    @Column(name = "start_time", nullable = false)
    private Date startTime;
    /**
     * The end time of the training session.
     * Must not be {@code null}.
     */
    @Column(name = "end_time", nullable = false)
    private Date endTime;
    /**
     * The type of physical activity performed during the training.
     * Stored as an ordinal value in the database.
     * Must not be {@code null}.
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "activity_type", nullable = false)
    private ActivityType activityType;
    /**
     * The total distance covered during the training, in kilometers.
     */
    @Column(name = "distance")
    private double distance;
    /**
     * The average speed maintained during the training, in kilometers per hour.
     */
    @Column(name = "average_speed")
    private double averageSpeed;
    /**
     * Constructs a new {@code Training} instance with the specified parameters.
     *
     * @param user          the user who performed the training
     * @param startTime     the start time of the training
     * @param endTime       the end time of the training
     * @param activityType  the type of activity performed
     * @param distance      the distance covered in kilometers
     * @param averageSpeed  the average speed in kilometers per hour
     */
    public Training(
            final User user,
            final Date startTime,
            final Date endTime,
            final ActivityType activityType,
            final double distance,
            final double averageSpeed) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }
}