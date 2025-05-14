package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserDtoBasic;
import pl.wsb.fitnesstracker.user.api.UserDtoEmail;

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
// DELETE http://localhost:8081/v1/users/15
    //dla elementów utworzonych przez dataInit wywala błąd Naruszenie więzów integralności: "FK32IR33U28FO97252HKSJVLUBP: PUBLIC.TRAININGS FOREIGN KEY(USER_ID) REFERENCES PUBLIC.USERS(ID) (CAST(9 AS BIGINT))
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    //GET http://localhost:8081/v1/users/byName?firstName=Emma&lastName=Johnson
    //GET http://localhost:8081/v1/users/byName?firstName=Ethan&lastName=Taylor
    @GetMapping("/byName")
    public List<User> getUserByName(@RequestParam String firstName, @RequestParam String lastName) {
        return userService.getUsersByName(firstName,lastName);
    }

    /*
    GET http://localhost:8081/v1/users/search?email=gra
[
    {
        "id": 9,
        "email": "Grace.Anderson@domain.com"
    }
]
     */
    @GetMapping("/search")
    public List<UserDtoEmail> getUserByEmail(@RequestParam String email) {
        return userService.findUserByEmailPartial(email);
    }

    /*
    GET http://localhost:8081/v1/users/searchOlder?age=60
[
    {
        "id": 3,
        "firstName": "Olivia",
        "lastName": "Davis",
        "birthdate": "1949-05-14",
        "email": "Olivia.Davis@domain.com"
    }
]
     */

    @GetMapping("/searchOlder")
    public List<User> getUsersOlderThan(@RequestParam int age) {
        return userService.getUsersOlderThan(age);
    }

}