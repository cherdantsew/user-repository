package com.orderengine.user.controller;

import com.orderengine.user.SpringBootApplicationTest;
import com.orderengine.user.model.dto.RegisterDataDto;
import com.orderengine.user.model.entity.User;
import com.orderengine.user.model.enumeration.AuthoritiesConstants;
import com.orderengine.user.model.enumeration.RolesConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static com.orderengine.user.util.Utils.randomString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public abstract class AbstractRegisterControllerTests extends SpringBootApplicationTest {

    private String regUrl;

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
    void shouldReturnBadRequestIfCreateCourierWithExistedLogin() throws Exception {
        if (needToAuthenticateUserBeforeRequest()) {
            authenticateAdmin();
        }
        var registerDataDto = new RegisterDataDto(randomString(), randomString());
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
