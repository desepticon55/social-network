package dev.bd.work.socialnetwork.utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.UUID;

/**
 * JWT utilities.
 *
 * @author Alexey Bodyak
 */
@UtilityClass
public class JwtUtils {

    /**
     * Extract user id from security context.
     *
     * @return user id
     * @throws IllegalStateException if user was not found in security context.
     */
    public UUID extractUserIdFromSecurityContext() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (Objects.nonNull(context)) {
            Authentication authentication = context.getAuthentication();
            if (Objects.nonNull(authentication)) {
                return UUID.fromString(authentication.getPrincipal().toString());
            }
            throw new IllegalStateException("No authentication found");
        }
        throw new IllegalStateException("No authentication found");
    }
}
