package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserDtoBasic;
import pl.wsb.fitnesstracker.user.api.UserDtoEmail;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
/**
 * REST controller handling user-related API endpoints for FitnessTracker.
 *
 * Provides CRUD operations and query methods for user data.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;
    /**
     * Retrieves a list of all users with full details.
     *
     * @return list of all users as UserDto
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
    /**
     * Creates a new user with the provided data.
     *
     * @param userDto the user data to create
     * @return the created user as UserDto
     * @throws InterruptedException if interrupted during processing
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) throws InterruptedException {
        User user = userMapper.toEntity(userDto);
        User createdUser = userService.createUser(user);
        return userMapper.toDto(createdUser);
    }
    /**
     * Retrieves a list of all users with basic details (ID, first name, last name).
     *
     * @return list of users as UserDtoBasic
     */
    @GetMapping("/simple")
    public List<UserDtoBasic> getBasicUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDtoBasic)
                .toList();
    }
    /**
     * Deletes the user identified by the given ID.
     *
     * @param id the ID of the user to delete
     */
    //dla elementów utworzonych przez dataInit wywala błąd Naruszenie więzów integralności: "FK32IR33U28FO97252HKSJVLUBP: PUBLIC.TRAININGS FOREIGN KEY(USER_ID) REFERENCES PUBLIC.USERS(ID) (CAST(9 AS BIGINT))
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }
    /**
     * Retrieves users matching the specified first name and last name.
     *
     * @param firstName user's first name to search for
     * @param lastName user's last name to search for
     * @return list of matching User entities
     */
    @GetMapping("/byName")
    public List<User> getUserByName(@RequestParam String firstName, @RequestParam String lastName) {
        return userService.getUsersByName(firstName,lastName);
    }
    /**
     * Retrieves a user by their unique ID.
     *
     * @param id the ID of the user
     * @return an Optional containing the User if found, or empty if not
     */
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }
    /**
     * Retrieves users whose email contains the specified partial string.
     *
     * @param email partial email to search for
     * @return list of matching users as UserDtoEmail
     */
    @GetMapping("/email")
    public List<UserDtoEmail> getUserByEmail(@RequestParam String email) {
        return userService.findUserByEmailPartial(email);
    }
    /**
     * Retrieves users older than the specified age.
     *
     * @param age the minimum age of users to retrieve
     * @return list of users older than the given age
     */
    @GetMapping("/olderThanAge/{age}")
    public List<User> getUsersOlderThanAge(@PathVariable int age) {
        return userService.getUsersOlderThanAge(age);
    }
    /**
     * Retrieves users older than the specified birthdate.
     *
     * @param time the birthdate threshold in yyyy-MM-dd format
     * @return list of users born before the given date
     */
    @GetMapping("/older/{time}")
    public List<User> getUsersOlderThan(@PathVariable String time) {
        return userService.getUsersOlderThan(LocalDate.parse(time));
    }
    /**
     * Updates the user with the given ID using provided new data.
     *
     * @param user_id the ID of the user to update
     * @param userDto the new user data
     * @return the updated user as UserDto
     */
    @PutMapping("/{user_id}")
    public UserDto updateUser(@PathVariable long user_id, @RequestBody UserDto userDto) {
        User updated = userService.updateUser(user_id, userDto);
        return userMapper.toDto(updated);
    }
}