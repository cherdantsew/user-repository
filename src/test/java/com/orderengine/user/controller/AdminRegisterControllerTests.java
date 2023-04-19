package com.orderengine.user.controller;

import com.orderengine.user.SpringBootApplicationTest;
import com.orderengine.user.model.dto.RegisterDataDto;
import com.orderengine.user.model.entity.User;
import com.orderengine.user.model.enumeration.RolesConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminRegisterControllerTests extends SpringBootApplicationTest {

    private static final String ADMIN_REGISTER_URL = "/admin/user-service/register";

    @Test
    void shouldReturnForbiddenIfHaveNoPermission() throws Exception {
        var registerDataDto = new RegisterDataDto("login1", "password1");
        mockmvc.perform(post(ADMIN_REGISTER_URL)
            .content(objectMapper.writeValueAsString(registerDataDto))
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isForbidden());
    }

    @Test
    void shouldReturnBadRequestIfCreateCourierWithAdminLogin() throws Exception {
        var registerDataDto = new RegisterDataDto("login", "password");
        String token = createToken("login", RolesConstants.ROLE_ADMIN, Collections.emptyList());
        mockmvc.perform(post(ADMIN_REGISTER_URL)
            .content(objectMapper.writeValueAsString(registerDataDto))
                .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }
}
