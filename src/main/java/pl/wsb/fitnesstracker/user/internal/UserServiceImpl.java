package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
/**
 * Implementation of user-related business logic for the FitnessTracker application.
 * <p>
 * Provides operations for creating, updating, deleting, and retrieving user data.
 * Combines the functionalities of {@link UserService} and {@link UserProvider}.
 */
@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    /**
     * Creates a new user.
     *
     * @param user the user to be created
     * @return the created user
     * @throws IllegalArgumentException if the user already has an ID
     */
    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }
    /**
     * Deletes the user with the specified ID.
     *
     * @param id the ID of the user to delete
     * @throws IllegalArgumentException if the ID is null or the user does not exist
     */
    @Override
    public void deleteUser(Long id) {
        log.info("Deleting User {}", id);
        if (id == null) {
            throw new IllegalArgumentException("User id is null!");
        }
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User with id " + id + " does not exist!");
        }
        userRepository.deleteById(id);
    }
    /**
     * Updates an existing user with the given ID using data from a {@link UserDto}.
     *
     * @param id the ID of the user to update
     * @param userDto the data to update the user with
     * @return the updated user
     * @throws IllegalArgumentException if the user does not exist
     */
    @Override
    public User updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " does not exist!"));
        log.info("Updating User {} to {}", user, userDto);
        user.setFirstName(userDto.firstName());
        user.setLastName(userDto.lastName());
        user.setBirthdate(userDto.birthdate());
        user.setEmail(userDto.email());
        return userRepository.save(user);
    }
    /**
     * Finds users whose email contains the specified substring (case-insensitive).
     *
     * @param email the partial email to search for
     * @return a list of users matching the partial email, as {@link UserDtoEmail}
     */
    @Override
    public List<UserDtoEmail> findUserByEmailPartial(String email) {
        return userRepository.findByEmailPartial(email)
                .stream()
                .map(userMapper::toDtoEmail)
                .toList();
    }
    /**
     * Retrieves all users who are older than the given age in years.
     *
     * @param years the minimum age
     * @return list of users older than the specified age
     */
    @Override
    public List<User> getUsersOlderThanAge(int years) {
        return userRepository.findByOlderThanAge(years);
    }
    /**
     * Retrieves users born before the specified date.
     *
     * @param date the birthdate threshold
     * @return list of users born before the given date
     */
    @Override
    public List<User> getUsersOlderThan(LocalDate date) {
        return userRepository.findByOlderThan(date);
    }
    /**
     * Retrieves a user by their unique ID.
     *
     * @param userId the user ID
     * @return an {@link Optional} containing the user if found, or empty if not
     */
    @Override
    public Optional<User> getUserById(final Long userId) {
        return userRepository.findById(userId);
    }
    /**
     * Retrieves all users in the system.
     *
     * @return list of all users
     */
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    /**
     * Retrieves users with the specified first and last name.
     *
     * @param firstName the first name to search for
     * @param lastName the last name to search for
     * @return list of users matching the name
     */
    @Override
    public List<User> getUsersByName(String firstName, String lastName) {
        return userRepository.findByName(firstName, lastName);
    }




}