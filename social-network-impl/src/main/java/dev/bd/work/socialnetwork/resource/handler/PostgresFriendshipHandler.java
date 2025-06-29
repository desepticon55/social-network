package dev.bd.work.socialnetwork.resource.handler;

import dev.bd.work.socialnetwork.exception.FriendshipException;
import dev.bd.work.socialnetwork.repository.FriendshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Postgres friendship handler.
 *
 * @author Alexey Bodyak
 */
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = "db.mode", havingValue = "postgres")
public class PostgresFriendshipHandler implements FriendshipHandler {

    private final FriendshipRepository repository;

    @Override
    @Transactional
    public void addFriend(UUID userId, UUID friendId) {
        if (userId.equals(friendId)) {
            throw new FriendshipException("Cannot add yourself as a friend");
        }

        if (repository.findByKey(userId, friendId).isPresent()) {
            throw new FriendshipException("Friendship already exists");
        }

        repository.createFriendship(userId, friendId);
    }

    @Override
    @Transactional
    public void removeFriend(UUID userId, UUID friendId) {
        if (repository.findByKey(userId, friendId).isEmpty()) {
            throw new FriendshipException("Friendship does not exist");
        }

        repository.deleteByKey(userId, friendId);
    }
}
