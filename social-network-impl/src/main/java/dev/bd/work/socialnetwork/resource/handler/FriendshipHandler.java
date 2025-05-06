package dev.bd.work.socialnetwork.resource.handler;

import dev.bd.work.socialnetwork.exception.FriendshipException;
import dev.bd.work.socialnetwork.repository.FriendshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Friendship handler.
 *
 * @author Alexey Bodyak
 */
@Component
@RequiredArgsConstructor
public class FriendshipHandler {

    private final FriendshipRepository repository;

    /**
     * Add friendship between two users.
     *
     * @param userId   user id
     * @param friendId friend id
     */
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

    /**
     * Remove friendship between two users.
     *
     * @param userId   user id
     * @param friendId friend id
     */
    @Transactional
    public void removeFriend(UUID userId, UUID friendId) {
        if (repository.findByKey(userId, friendId).isEmpty()) {
            throw new FriendshipException("Friendship does not exist");
        }

        repository.deleteByKey(userId, friendId);
    }
}
