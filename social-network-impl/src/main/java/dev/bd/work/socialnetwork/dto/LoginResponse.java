package dev.bd.work.socialnetwork.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

/**
 * Auth response.
 *
 * @author Alexey Bodyak
 */
@Data
@Accessors(chain = true)
public class LoginResponse {
    private String token;

    public static LoginResponse of(@NonNull String token) {
        return new LoginResponse().setToken(token);
    }
}
