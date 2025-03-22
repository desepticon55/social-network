package dev.bd.work.socialnetwork;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

/**
 * @author Alexey_Bodyak
 */
public class AutoCloseableMockTest {

    private AutoCloseable autoCloseable;

    @BeforeEach
    public void initAutoCloseable() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void closeAutoCloseable() throws Exception {
        autoCloseable.close();
    }
}
