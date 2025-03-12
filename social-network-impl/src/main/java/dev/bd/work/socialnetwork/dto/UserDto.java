package dev.bd.work.socialnetwork.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.UUID;

/**
 * User dto.
 *
 * @author Alexey Bodyak
 */
@Data
@Accessors(chain = true)
public class UserDto {
    private UUID id;
    private String firstName;
    private String secondName;
    private LocalDate birthdate;
    private String biography;
    private String city;
}
