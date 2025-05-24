package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserDtoBasic;
import pl.wsb.fitnesstracker.user.api.UserDtoEmail;
/**
 * Component responsible for mapping between {@link User} entities and various DTO representations.
 */
@Component
class UserMapper {
    /**
     * Converts a {@link User} entity to a {@link UserDto}.
     *
     * @param user the User entity to convert
     * @return the corresponding UserDto
     */
    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }
    /**
     * Converts a {@link User} entity to a {@link UserDtoBasic}, containing only basic info.
     *
     * @param user the User entity to convert
     * @return the corresponding UserDtoBasic
     */
    UserDtoBasic toDtoBasic(User user) {
        return new UserDtoBasic(user.getId(),
                user.getFirstName(),
                user.getLastName());
    }
    /**
     * Converts a {@link UserDto} to a {@link User} entity.
     * Note: the returned entity does not include the ID field.
     *
     * @param userDto the UserDto to convert
     * @return the corresponding User entity
     */
    User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }
    /**
     * Converts a {@link User} entity to a {@link UserDtoEmail}, containing only ID and email.
     *
     * @param user the User entity to convert
     * @return the corresponding UserDtoEmail
     */
    UserDtoEmail toDtoEmail(User user) {
        return new UserDtoEmail(user.getId(),
                user.getEmail());
    }

}
