package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserDtoBasic;
import pl.wsb.fitnesstracker.user.api.UserDtoEmail;

@Component
class UserMapper {

    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    UserDtoBasic toDtoBasic(User user) {
        return new UserDtoBasic(user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }

    UserDtoEmail toDtoEmail(User user) {
        return new UserDtoEmail(user.getId(),
                user.getEmail());
    }

}
