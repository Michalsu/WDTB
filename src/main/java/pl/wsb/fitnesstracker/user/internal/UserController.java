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

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) throws InterruptedException {
        User user = userMapper.toEntity(userDto);
        User createdUser = userService.createUser(user);
        return userMapper.toDto(createdUser);
    }

    @GetMapping("/simple")
    public List<UserDtoBasic> getBasicUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDtoBasic)
                .toList();
    }
// DELETE http://localhost:8081/v1/users/15
    //dla elementów utworzonych przez dataInit wywala błąd Naruszenie więzów integralności: "FK32IR33U28FO97252HKSJVLUBP: PUBLIC.TRAININGS FOREIGN KEY(USER_ID) REFERENCES PUBLIC.USERS(ID) (CAST(9 AS BIGINT))
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    //GET http://localhost:8081/v1/users/byName?firstName=Emma&lastName=Johnson
    //GET http://localhost:8081/v1/users/byName?firstName=Ethan&lastName=Taylor
    @GetMapping("/byName")
    public List<User> getUserByName(@RequestParam String firstName, @RequestParam String lastName) {
        return userService.getUsersByName(firstName,lastName);
    }


    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/email")
    public List<UserDtoEmail> getUserByEmail(@RequestParam String email) {
        return userService.findUserByEmailPartial(email);
    }

    @GetMapping("/olderThanAge/{age}")
    public List<User> getUsersOlderThanAge(@PathVariable int age) {
        return userService.getUsersOlderThanAge(age);
    }

    @GetMapping("/older/{time}")
    public List<User> getUsersOlderThan(@PathVariable String time) {
        return userService.getUsersOlderThan(LocalDate.parse(time));
    }

    @PutMapping("/{user_id}")
    public UserDto updateUser(@PathVariable long user_id, @RequestBody UserDto userDto) {
        User updated = userService.updateUser(user_id, userDto);
        return userMapper.toDto(updated);
    }

}