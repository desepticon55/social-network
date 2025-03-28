package dev.bd.work.socialnetwork.repository;

import dev.bd.work.socialnetwork.model.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * Repository to {@link User} model.
 *
 * @author Alexey Bodyak
 */
public interface UserRepository extends CrudRepository<User, UUID> {

    @Query("select * from ntwrk_user where first_name like :firstName and second_name like :secondName order by id")
    List<User> findAllByFirstNameAndSecondNamePrefix(@Param("firstName") String firstName, @Param("secondName") String secondName);
}
