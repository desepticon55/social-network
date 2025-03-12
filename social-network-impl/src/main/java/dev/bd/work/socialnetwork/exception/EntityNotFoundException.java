package dev.bd.work.socialnetwork.exception;

import lombok.Getter;

/**
 * Entity not found exception.
 *
 * @author Alexey Bodyak
 */
@Getter
public class EntityNotFoundException extends RuntimeException {

    private final Class<?> entityClass;

    public EntityNotFoundException(Class<?> entityClass) {
        this.entityClass = entityClass;
    }
}
