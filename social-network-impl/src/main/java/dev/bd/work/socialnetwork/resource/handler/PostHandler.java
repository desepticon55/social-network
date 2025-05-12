package dev.bd.work.socialnetwork.resource.handler;

import dev.bd.work.socialnetwork.dto.PostCreateRequest;
import dev.bd.work.socialnetwork.dto.PostDto;
import dev.bd.work.socialnetwork.dto.PostUpdateRequest;
import dev.bd.work.socialnetwork.exception.EntityNotFoundException;
import dev.bd.work.socialnetwork.mapper.PostDtoMapper;
import dev.bd.work.socialnetwork.model.Friendship;
import dev.bd.work.socialnetwork.model.Post;
import dev.bd.work.socialnetwork.repository.FriendshipRepository;
import dev.bd.work.socialnetwork.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Post handler.
 *
 * @author Alexey Bodyak
 */
@Component
@RequiredArgsConstructor
public class PostHandler {

    private static final String FEED_CACHE_NAME = "userFeedCache";

    private final CacheManager cacheManager;
    private final PostDtoMapper postDtoMapper;
    private final PostRepository postRepository;
    private final FriendshipRepository friendshipRepository;

    /**
     * Create post.
     *
     * @param userId  user id
     * @param request request
     * @return updated post
     */
    @Transactional
    public PostDto createPost(UUID userId, PostCreateRequest request) {
        invalidateFriendsCache(userId);
        return postDtoMapper.toDto(postRepository.save(makePost(userId, request)));
    }

    /**
     * Update post.
     *
     * @param userId  user id
     * @param request request
     * @return updated post
     */
    @Transactional
    public PostDto updatePost(UUID userId, PostUpdateRequest request) {
        invalidateFriendsCache(userId);
        return postRepository.findById(request.getId())
                .map(it -> it.setPostText(request.getText()))
                .map(it -> postDtoMapper.toDto(postRepository.save(it)))
                .orElseThrow(() -> new EntityNotFoundException(Post.class));
    }

    /**
     * Find post by id.
     *
     * @param postId post id
     * @return updated post
     */
    public PostDto findById(UUID postId) {
        return postRepository.findById(postId)
                .map(postDtoMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(Post.class));
    }

    /**
     * Delete post.
     *
     * @param postId post id
     * @param userId user id
     */
    @Transactional
    public void deletePost(UUID postId, UUID userId) {
        invalidateFriendsCache(userId);
        postRepository.deleteById(postId);
    }

    /**
     * Find feed to current user.
     *
     * @param userId   user id
     * @param pageable pageable
     * @return list of posts
     */
    @Cacheable(value = FEED_CACHE_NAME, key = "#userId", sync = true)
    public List<PostDto> findFeed(UUID userId, Pageable pageable) {
        return postDtoMapper.toDto(postRepository.findFriendsPosts(
                userId, pageable.getOffset(), pageable.getPageSize()
        ));
    }

    private void invalidateFriendsCache(UUID userId) {
        List<Friendship> friends = friendshipRepository.findAllFriends(userId);
        for (Friendship friend : friends) {
            Cache cache = cacheManager.getCache(FEED_CACHE_NAME);
            if (Objects.nonNull(cache)) {
                cache.evict(friend.getUserId());
            }
        }
    }

    private Post makePost(UUID userId, PostCreateRequest request) {
        return new Post().setPostText(request.getText()).setAuthorId(userId);
    }
}
