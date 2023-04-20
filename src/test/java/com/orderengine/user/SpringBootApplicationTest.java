package com.orderengine.user;

import com.orderengine.user.config.TestBeansConfig;
import com.orderengine.user.config.TokenProvider;
import com.orderengine.user.model.enumeration.AuthoritiesConstants;
import com.orderengine.user.model.enumeration.RolesConstants;
import com.orderengine.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@Import(TestBeansConfig.class)
public class SpringBootApplicationTest extends TestPostgresContainer {

    @Autowired
    protected MockMvc mockmvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    protected PasswordEncoder bCryptPasswordEncoder;

    protected String authenticateAndReturnToken(String username, RolesConstants currentRole, List<AuthoritiesConstants> authorities) {
        TokenProvider.JwtTokenConfiguration jwtTokenConfiguration = new TokenProvider.JwtTokenConfiguration(username, authorities.stream().map(AuthoritiesConstants::name).collect(Collectors.joining()), currentRole.name());
        String jwt = tokenProvider.createToken(jwtTokenConfiguration);
        Authentication authentication = tokenProvider.getAuthentication(jwt);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return String.format("Bearer %s", jwt);
    }
}
