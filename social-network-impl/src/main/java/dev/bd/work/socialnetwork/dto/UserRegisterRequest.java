package dev.bd.work.socialnetwork.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * Request to register user.
 *
 * @author Alexey Bodyak
 */
@Data
@Accessors(chain = true)
public class UserRegisterRequest {
    private String firstName;
    private String secondName;
    private LocalDate birthdate;
    private String biography;
    private String city;
    private String password;
}
