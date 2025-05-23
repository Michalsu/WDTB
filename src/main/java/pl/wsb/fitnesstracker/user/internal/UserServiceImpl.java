package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

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


    @Override
    public User updateEmail(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " does not exist!"));
        user.setFirstName(userDto.firstName());
        user.setLastName(userDto.lastName());
        user.setBirthdate(userDto.birthdate());
        user.setEmail(userDto.email());
        return userRepository.save(user);
    }

    @Override
    public List<UserDtoEmail> findUserByEmailPartial(String email) {
        return userRepository.findByEmailPartial(email)
                .stream()
                .map(userMapper::toDtoEmail)
                .toList();
    }

    @Override
    public List<User> getUsersOlderThanAge(int years) {
        return userRepository.findByOlderThanAge(years);
    }

    /**
     * @param birthdate
     * @return
     */
    @Override
    public List<User> getUsersOlderThan(LocalDate birthdate) {
        return userRepository.findByOlderThan(birthdate);
    }

    @Override
    public Optional<User> getUserById(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersByName(String firstName, String lastName) {
        return userRepository.findByName(firstName, lastName);
    }




}