package dev.bd.work.socialnetwork;

import static dev.khbd.interp4j.core.Interpolations.s;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.MountableFile;

import java.time.Duration;

/**
 * @author Alexey_Bodyak
 */
@Slf4j
@ActiveProfiles("test")
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {

    private static GenericContainer postgres = new GenericContainer("postgres:17.4")
            .withExposedPorts(5432)
            .withEnv("POSTGRES_DB", "ib")
            .withEnv("POSTGRES_USER", "postgres")
            .withEnv("POSTGRES_PASSWORD", "admin")
            .withStartupTimeout(Duration.ofMinutes(10L))
            .withCopyFileToContainer(
                    MountableFile.forClasspathResource("init.sql"),
                    "/docker-entrypoint-initdb.d/01-init.sql"
            );

    static {
        postgres.start();
        log.info("Postgres container started. Address: {}, port: {}", postgres.getHost(), postgres.getFirstMappedPort());
    }

    @Autowired
    private DatabaseTruncateService truncateService;

    @BeforeEach
    public void init() {
        truncateService.truncate();
    }

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        private static final String JDBC_URL = s("jdbc:postgresql://${postgres.getHost()}:${postgres.getFirstMappedPort()}/ib?currentSchema=ntwrk");

        private static String[] springApplicationProperties() {
            return new String[]{
                    "spring.datasource.url=" + JDBC_URL,
                    "spring.datasource.username=ntwrk_ms",
                    "spring.datasource.password=ntwrk_ms",
                    "liquibase.url=" + JDBC_URL,
                    "liquibase.user=ntwrk",
                    "liquibase.password=ntwrk"
            };
        }

        @Override
        public void initialize(ConfigurableApplicationContext context) {
            TestPropertyValues.of(springApplicationProperties())
                    .applyTo(context.getEnvironment(), TestPropertyValues.Type.SYSTEM_ENVIRONMENT, "testcontainers");
        }
    }
}
