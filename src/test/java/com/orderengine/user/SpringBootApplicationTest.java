package com.orderengine.user;

import com.orderengine.user.config.TestBeansConfig;
import com.orderengine.user.config.TokenProvider;
import com.orderengine.user.model.enumeration.AuthoritiesConstants;
import com.orderengine.user.model.enumeration.RolesConstants;
import com.orderengine.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
//    (classes = {UserApplicationTests.class},
//    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
//)
@Testcontainers
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(initializers = {SpringBootApplicationTest.Initializer.class})
@ActiveProfiles("test")
@Import(TestBeansConfig.class)
public class SpringBootApplicationTest {

    @Autowired
    protected MockMvc mockmvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    private TokenProvider tokenProvider;

    private static final String DATABASE_NAME = "users";

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:11.1")
        .withReuse(true)
        .withDatabaseName(DATABASE_NAME);

    protected String createToken(String username, RolesConstants currentRole, List<AuthoritiesConstants> authorities) {
        TokenProvider.JwtTokenConfiguration jwtTokenConfiguration = new TokenProvider.JwtTokenConfiguration(username, authorities.stream().map(AuthoritiesConstants::name).collect(Collectors.joining()), currentRole.name());
        String jwt = tokenProvider.createToken(jwtTokenConfiguration);
        return String.format("Bearer %s", jwt);
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                "CONTAINER.USERNAME=" + postgreSQLContainer.getUsername(),
                "CONTAINER.PASSWORD=" + postgreSQLContainer.getPassword(),
                "CONTAINER.URL=" + postgreSQLContainer.getJdbcUrl()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
