package dev.bd.work.socialnetwork.resource.handler;

import static dev.khbd.interp4j.core.Interpolations.s;

import dev.bd.work.socialnetwork.exception.FriendshipException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

/**
 * Redis friendship handler.
 *
 * @author Alexey Bodyak
 */
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = "db.mode", havingValue = "redis")
public class RedisFriendshipHandler implements FriendshipHandler {

    private final StringRedisTemplate redisTemplate;

    @Override
    @Transactional
    public void addFriend(UUID userId, UUID friendId) {
        String script = loadScript("lua/add_friend.lua");

        DefaultRedisScript<String> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(script);
        redisScript.setResultType(String.class);

        List<String> args = List.of(userId.toString(), friendId.toString());

        String result = redisTemplate.execute(redisScript, List.of(), args.toArray());

        if (!"OK".equals(result)) {
            throw new FriendshipException(result);
        }
    }

    @Override
    @Transactional
    public void removeFriend(UUID userId, UUID friendId) {
        throw new NotImplementedException();
    }

    private String loadScript(String path) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(path)) {
            if (is == null) {
                throw new IllegalArgumentException(s("Lua script not found: ${path}"));
            }
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
