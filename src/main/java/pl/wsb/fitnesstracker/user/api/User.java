package pl.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * Represents a user in the FitnessTracker system.
 *
 * This class is mapped to the "users" table in the database.
 * It contains basic personal information about the user such as
 * first name, last name, birthdate, and email address.
 */
@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User {
    /**
     * Unique identifier of the user.
     * Generated automatically by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;
    /**
     * User's first name.
     */
    @Setter
    @Column(name = "first_name", nullable = false)
    private String firstName;
    /**
     * User's last name.
     */
    @Setter
    @Column(name = "last_name", nullable = false)
    private String lastName;
    /**
     * User's birth date.
     */
    @Setter
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    /**
     * User's email address.
     * Must be unique in the database.
     */
    @Setter
    @Column(nullable = false, unique = true)
    private String email;
    /**
     * Constructs a new User with the specified details.
     *
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param birthdate the birth date of the user
     * @param email the unique email address of the user
     */
    public User(
            final String firstName,
            final String lastName,
            final LocalDate birthdate,
            final String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
    }

}

