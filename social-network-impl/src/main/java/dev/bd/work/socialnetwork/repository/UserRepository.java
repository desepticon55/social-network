package dev.bd.work.socialnetwork.repository;

import dev.bd.work.socialnetwork.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Repository to {@link User} model.
 *
 * @author Alexey Bodyak
 */
public interface UserRepository extends CrudRepository<User, UUID> {
}
