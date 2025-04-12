package dev.bd.work.socialnetwork.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

/**
 * Response to register user.
 *
 * @author Alexey Bodyak
 */
@Data
@Accessors(chain = true)
public class UserRegisterResponse {
    private UUID userId;

    public static UserRegisterResponse of(UUID userId) {
        return new UserRegisterResponse().setUserId(userId);
    }
}
