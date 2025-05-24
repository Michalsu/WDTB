package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
/**
 * Repository interface for accessing {@link User} entities from the database.
 *
 * Extends {@link JpaRepository} to provide basic CRUD operations.
 * Includes several default methods for in-memory filtering of users.
 */
interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }
    /**
     * Searches for users with a specific first name and last name.
     *
     * @param firstName the first name to match
     * @param lastName the last name to match
     * @return list of users with matching names
     */
    default List<User> findByName(String firstName, String lastName){
        return findAll().stream()
                .filter(user -> Objects.equals(user.getFirstName(), firstName))
                .filter(user -> Objects.equals(user.getLastName(), lastName))
                .toList();
    }
    /**
     * Searches for users whose email contains the specified string, case-insensitively.
     *
     * @param email a partial string to match within email addresses
     * @return list of users whose email contains the given string
     */
    default List<User> findByEmailPartial(String email) {
        return findAll().stream()
                .filter(user -> user.getEmail().toLowerCase().contains(email.toLowerCase()))
                .toList();
    }

    /**
     * Retrieves users older than the specified age.
     *
     * @param years the minimum age in years
     * @return list of users who are older than the given age
     */
    default List<User> findByOlderThanAge(int years) {
        return findAll().stream()
                .filter((user -> user.getBirthdate().plusYears(years).isBefore(LocalDate.now())))
                .toList();
    }
    /**
     * Retrieves users born before the specified date.
     *
     * @param birthdate the cutoff birthdate
     * @return list of users born before the given date
     */
    default List<User> findByOlderThan(LocalDate birthdate) {
        return findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(birthdate))
                .toList();
    }

}
