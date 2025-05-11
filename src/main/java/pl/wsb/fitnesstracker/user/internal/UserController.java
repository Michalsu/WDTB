package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserDtoBasic;

import java.util.List;

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
/*
    POST http://localhost:8081/v1/users
{
  "firstName": "Michał",
  "lastName": "Michalski",
  "birthdate": "1999-03-19",
  "email": "michal.michalski@mail.com"
}

 */
    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto) throws InterruptedException {
        User user = userMapper.toEntity(userDto);
        User createdUser = userService.createUser(user);
        return userMapper.toDto(createdUser);
    }
    //GET http://localhost:8081/v1/users/basic
    @GetMapping("/basic")
    public List<UserDtoBasic> getBasicUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDtoBasic)
                .toList();
    }

    //GET http://localhost:8081/v1/users/byName?firstName=Emma&lastName=Johnson
    //GET http://localhost:8081/v1/users/byName?firstName=Ethan&lastName=Taylor
    @GetMapping("/byName")
    public List<User> getUserByName(@RequestParam String firstName, @RequestParam String lastName) {
        return userService.getUsersByName(firstName,lastName);
    }

}