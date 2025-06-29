package dev.bd.work.socialnetwork.resource.handler;

import java.util.UUID;

/**
 * @author Alexey Bodyak
 */
public interface FriendshipHandler {

    /**
     * Add friendship between two users.
     *
     * @param userId   user id
     * @param friendId friend id
     */
    void addFriend(UUID userId, UUID friendId);

    /**
     * Remove friendship between two users.
     *
     * @param userId   user id
     * @param friendId friend id
     */
    void removeFriend(UUID userId, UUID friendId);
}
