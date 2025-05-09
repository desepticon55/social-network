package dev.bd.work.socialnetwork.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

/**
 * Post model.
 *
 * @author Alexey Bodyak
 */
@Data
@Accessors(chain = true)
@Table(name = "ntwrk_post")
public class Post {

    public Post() {
        this.id = UUID.randomUUID();
    }

    @Id
    private UUID id;
    private UUID authorId;
    private String postText;
    private Instant createdAt;
    @Version
    private Long version;
}
