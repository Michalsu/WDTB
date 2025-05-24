package pl.wsb.fitnesstracker.user.api;
import jakarta.annotation.Nullable;

/**
 * Basic Data Transfer Object (DTO) representing simplified user data.
 *
 * Contains only the user’s ID and basic personal information: first name and last name.
 * Useful for scenarios where full user details are not required.
 *
 * @param id the unique identifier of the user; may be {@code null}
 * @param firstName the user's first name
 * @param lastName the user's last name
 */
public record UserDtoBasic(@Nullable Long id, String firstName, String lastName){

}
