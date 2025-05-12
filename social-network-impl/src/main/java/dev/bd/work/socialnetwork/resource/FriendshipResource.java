package dev.bd.work.socialnetwork.resource;

import dev.bd.work.socialnetwork.resource.handler.FriendshipHandler;
import dev.bd.work.socialnetwork.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Friendship resource.
 *
 * @author Alexey Bodyak
 */
@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendshipResource {

    private final FriendshipHandler handler;

    @PutMapping("/set/{friendId}")
    public void addFriend(@PathVariable UUID friendId) {
        UUID userId = JwtUtils.extractUserIdFromSecurityContext();
        handler.addFriend(userId, friendId);
    }

    @PutMapping("/delete/{friendId}")
    public void removeFriend(@PathVariable UUID friendId) {
        UUID userId = JwtUtils.extractUserIdFromSecurityContext();
        handler.removeFriend(userId, friendId);
    }
}
