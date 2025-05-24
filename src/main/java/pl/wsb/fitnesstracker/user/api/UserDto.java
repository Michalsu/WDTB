package pl.wsb.fitnesstracker.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;
/**
 * Data Transfer Object (DTO) representing a user.
 *
 * Used for transferring user data between application layers or through APIs.
 *
 * @param id the unique identifier of the user; may be {@code null}
 * @param firstName the user's first name
 * @param lastName the user's last name
 * @param birthdate the user's birth date, formatted as "yyyy-MM-dd"
 * @param email the user's email address
 */
public record UserDto(@Nullable Long id, String firstName, String lastName,
                      @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
                      String email) {

}
