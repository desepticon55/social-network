package dev.bd.work.socialnetwork.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

/**
 * Auth request.
 *
 * @author Alexey Bodyak
 */
@Data
@Accessors(chain = true)
public class LoginRequest {
    private UUID userId;
    private String password;
}
