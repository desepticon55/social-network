package dev.bd.work.socialnetwork.resource;

import dev.bd.work.socialnetwork.dto.PostDto;
import dev.bd.work.socialnetwork.resource.handler.PostHandler;
import dev.bd.work.socialnetwork.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * Post resource.
 *
 * @author Alexey Bodyak
 */
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostResource {

    private final PostHandler handler;

    @GetMapping("/feed")
    public List<PostDto> findFeed(Pageable pageable) {
        UUID userId = JwtUtils.extractUserIdFromSecurityContext();
        return handler.findFeed(userId, pageable);
    }
}
