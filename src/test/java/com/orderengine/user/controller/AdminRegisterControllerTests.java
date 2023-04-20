package com.orderengine.user.controller;

import com.orderengine.user.SpringBootApplicationTest;
import com.orderengine.user.model.dto.RegisterDataDto;
import com.orderengine.user.model.dto.UserAuthDataDto;
import com.orderengine.user.model.entity.User;
import com.orderengine.user.model.enumeration.RolesConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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
    void shouldReturn200ifregisteradminwithadminrole() throws Exception {
        var registerDataDto = new RegisterDataDto("login", "password");
        String token = createToken("login", RolesConstants.ROLE_ADMIN, Collections.emptyList());
        MvcResult mvcResult = mockmvc.perform(post(ADMIN_REGISTER_URL)
            .content(objectMapper.writeValueAsString(registerDataDto))
            .header(HttpHeaders.AUTHORIZATION, token)
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8")
        ).andExpect(status().isOk()).andReturn();
    }

    @Test
    void shouldAuthenticateAdminAndReturnToken() throws Exception {
        var registerDataDto = new UserAuthDataDto("login", "password");
        MvcResult mvcResult = mockmvc.perform(post("/admin/user-service/authenticate")
            .content(objectMapper.writeValueAsString(registerDataDto))
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8")
        ).andExpect(status().isOk()).andReturn();
    }


}
