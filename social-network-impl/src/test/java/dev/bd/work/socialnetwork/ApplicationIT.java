package dev.bd.work.socialnetwork;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * @author Alexey_Bodyak
 */
class ApplicationIT extends AbstractIntegrationTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void checkStartupContext_contextIsStarted_returnStartupDate() {
        long startupDate = context.getStartupDate();

        assertThat(startupDate).isGreaterThan(0);
    }
}
