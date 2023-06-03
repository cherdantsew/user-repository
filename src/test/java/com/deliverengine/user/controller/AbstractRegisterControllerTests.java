package com.deliverengine.user.controller;

import com.deliverengine.core.enumeration.AuthoritiesConstants;
import com.deliverengine.core.enumeration.RolesConstants;
import com.deliverengine.user.SpringBootApplicationTest;
import com.deliverengine.user.model.dto.RegisterDataDto;
import com.deliverengine.user.model.entity.User;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.deliverengine.user.util.Utils.randomString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public abstract class AbstractRegisterControllerTests extends SpringBootApplicationTest {

    private final String regUrl;

    public AbstractRegisterControllerTests(String regUrl) {
        this.regUrl = regUrl;
    }

    @Test
    void shouldCreateUser() throws Exception {
        if (needToAuthenticateUserBeforeRequest()) {
            authenticateAdmin();
        }
        var registerDataDto = new RegisterDataDto(randomString(), randomString());
        mockmvc.perform(post(regUrl)
            .content(objectMapper.writeValueAsString(registerDataDto))
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        User user = userRepository.findByLogin(registerDataDto.getLogin());
        Assertions.assertNotEquals(user.getPassword(), registerDataDto.getPassword());
    }

    @Test
    void shouldReturnBadRequestIfCreateUserWithExistedLogin() throws Exception {
        if (needToAuthenticateUserBeforeRequest()) {
            authenticateAdmin();
        }
        var registerDataDto = new RegisterDataDto("login", randomString());
        mockmvc.perform(post(regUrl)
            .content(objectMapper.writeValueAsString(registerDataDto))
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    protected abstract Boolean needToAuthenticateUserBeforeRequest();

    private void authenticateAdmin(){
        super.authenticateAndReturnToken(randomString(), RolesConstants.ROLE_ADMIN, List.of(AuthoritiesConstants.CURRENT_USER));
    }
}
