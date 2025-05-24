package pl.wsb.fitnesstracker.user.api;

/**
 * Data Transfer Object (DTO) containing the user's ID and email address.
 *
 * Useful in cases where only the user's identifier and email are needed.
 *
 * @param id the unique identifier of the user
 * @param email the user's email address
 */
public record UserDtoEmail(Long id, String email) {
}
