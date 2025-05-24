package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserProvider {

    /**
     * Retrieves a user based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param userId id of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUserById(Long userId);

    /**
     * Retrieves all users.
     *
     * @return An {@link Optional} containing the all users,
     */
    List<User> findAllUsers();
    /**
     * Retrieves a list of users matching the given first name and last name.
     *
     * @param firstName the first name to search for
     * @param lastName the last name to search for
     * @return a list of users with the specified first and last name
     */
    List<User> getUsersByName(String firstName, String lastName);
    /**
     * Finds users whose email contains the specified partial string.
     *
     * @param email a partial email string to search for (case insensitive)
     * @return a list of users matching the partial email, represented as UserDtoEmail objects
     */
    List<UserDtoEmail> findUserByEmailPartial(String email);
    /**
     * Retrieves users older than the specified age in years.
     *
     * @param years the minimum age of users to retrieve
     * @return a list of users older than the given age
     */
    List<User> getUsersOlderThanAge(int years);
    /**
     * Retrieves users older than the specified date.
     *
     * @param date the date threshold; users born before this date will be returned
     * @return a list of users born before the specified birthdate
     */
    List<User> getUsersOlderThan(LocalDate date);
}
