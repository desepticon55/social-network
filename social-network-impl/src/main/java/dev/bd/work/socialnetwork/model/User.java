package dev.bd.work.socialnetwork.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

/**
 * User model.
 *
 * @author Alexey Bodyak
 */
@Data
@Accessors(chain = true)
@Table(name = "ntwrk_user")
public class User {

    public User() {
        this.id = UUID.randomUUID();
    }

    @Id
    private UUID id;
    private String password;
    private String firstName;
    private String secondName;
    private LocalDate birthdate;
    private String biography;
    private String city;
    @Version
    private Long version;
}
