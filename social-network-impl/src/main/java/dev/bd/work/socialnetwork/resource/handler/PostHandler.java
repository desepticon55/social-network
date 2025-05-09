package dev.bd.work.socialnetwork.resource.handler;

import dev.bd.work.socialnetwork.dto.PostDto;
import dev.bd.work.socialnetwork.mapper.PostDtoMapper;
import dev.bd.work.socialnetwork.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Post handler.
 *
 * @author Alexey Bodyak
 */
@Component
@RequiredArgsConstructor
public class PostHandler {

    private final PostDtoMapper postDtoMapper;
    private final PostRepository postRepository;

    /**
     * Find feed to current user.
     *
     * @param userId   user id
     * @param pageable pageable
     * @return list of posts
     */
    public List<PostDto> findFeed(UUID userId, Pageable pageable) {
        return postDtoMapper.toDto(postRepository.findFriendsPosts(
                userId, pageable.getOffset(), pageable.getPageSize()
        ));
    }
}
