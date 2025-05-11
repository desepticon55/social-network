package dev.bd.work.socialnetwork.repository;

import dev.bd.work.socialnetwork.model.Friendship;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Friendship repository.
 *
 * @author Alexey Bodyak
 */
public interface FriendshipRepository extends CrudRepository<Friendship, UUID> {

    @Query("select * from ntwrk_friend f where f.user_id = :userId and f.friend_id = :friendId")
    Optional<Friendship> findByKey(@Param("userId") UUID userId, @Param("friendId") UUID friendId);

    @Query("select * from ntwrk_friend f where f.user_id = :userId")
    List<Friendship> findAllFriends(@Param("userId") UUID userId);

    @Modifying
    @Transactional
    @Query("delete from ntwrk_friend f where f.user_id = :userId and f.friend_id = :friendId")
    void deleteByKey(@Param("userId") UUID userId, @Param("friendId") UUID friendId);

    @Modifying
    @Transactional
    @Query("insert into ntwrk_friend(user_id, friend_id) values (:userId, :friendId)")
    void createFriendship(@Param("userId") UUID userId, @Param("friendId") UUID friendId);
}
