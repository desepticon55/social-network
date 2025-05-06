package dev.bd.work.socialnetwork.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

/**
 * Friendship model.
 *
 * @author Alexey Bodyak
 */
@Data
@Accessors(chain = true)
@Table(name = "ntwrk_friend")
public class Friendship {
    private UUID userId;
    private UUID friendId;
}
