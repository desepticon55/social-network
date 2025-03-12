package dev.bd.work.socialnetwork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Alexey_Bodyak
 */
@Service
@Profile("test")
public class DatabaseTruncateService {

    private static final List<String> TABLES = List.of(
            "user"
    );

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void truncate() {
        TABLES.stream()
                .map(n -> "ntwrk_" + n)
                .map(n -> "delete from " + n)
                .forEach(jdbcTemplate::execute);
    }
}
