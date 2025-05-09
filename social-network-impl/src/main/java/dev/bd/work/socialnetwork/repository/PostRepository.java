package dev.bd.work.socialnetwork.repository;

import dev.bd.work.socialnetwork.model.Post;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * Repository to {@link Post} model.
 *
 * @author Alexey Bodyak
 */
public interface PostRepository extends CrudRepository<Post, UUID>, PagingAndSortingRepository<Post, UUID> {

    @Query("select p.* from ntwrk_post p join ntwrk_friend f ON p.author_id = f.friend_id" +
            " where f.user_id = :userId order by p.created_at desc offset :offset limit :limit")
    List<Post> findFriendsPosts(@Param("userId") UUID userId, @Param("offset") long offset, @Param("limit") int limit);
}
