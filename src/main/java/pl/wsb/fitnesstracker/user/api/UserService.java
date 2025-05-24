package pl.wsb.fitnesstracker.user.api;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {
    /**
     * Creates a new user in the system.
     *
     * @param user the User entity to create
     * @return the created User entity with any generated fields populated
     */
    User createUser(User user);
    /**
     * Deletes the user identified by the given ID.
     *
     * @param id the unique identifier of the user to delete
     */
    void deleteUser(Long id);
    /**
     * Updates the user identified by the given ID with new data.
     *
     * @param id the unique identifier of the user to update
     * @param userDto the data transfer object containing updated user information
     * @return the updated User entity
     */
    User updateUser(Long id, UserDto userDto);


}

