package dev.bd.work.socialnetwork.resource;

import dev.bd.work.socialnetwork.dto.PostCreateRequest;
import dev.bd.work.socialnetwork.dto.PostDto;
import dev.bd.work.socialnetwork.dto.PostUpdateRequest;
import dev.bd.work.socialnetwork.resource.handler.PostHandler;
import dev.bd.work.socialnetwork.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/create")
    public PostDto createPost(@RequestBody PostCreateRequest request) {
        UUID userId = JwtUtils.extractUserIdFromSecurityContext();
        return handler.createPost(userId, request);
    }

    @PutMapping("/update")
    public PostDto updatePost(@RequestBody PostUpdateRequest request) {
        UUID userId = JwtUtils.extractUserIdFromSecurityContext();
        return handler.updatePost(userId, request);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable("id") UUID postId) {
        UUID userId = JwtUtils.extractUserIdFromSecurityContext();
        handler.deletePost(postId, userId);
    }

    @GetMapping("/get/{id}")
    public PostDto findPost(@PathVariable("id") UUID postId) {
        return handler.findById(postId);
    }

    @GetMapping("/feed")
    public List<PostDto> findFeed(Pageable pageable) {
        UUID userId = JwtUtils.extractUserIdFromSecurityContext();
        return handler.findFeed(userId, pageable);
    }
}
