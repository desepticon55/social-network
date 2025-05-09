package dev.bd.work.socialnetwork.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.UUID;

/**
 * Post dto.
 *
 * @author Alexey Bodyak
 */
@Data
@Accessors(chain = true)
public class PostDto {
    private UUID id;
    private String postText;
    private UUID authorId;
    private Instant createdAt;
}
