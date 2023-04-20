package com.orderengine.user.controller;

import com.orderengine.user.SpringBootApplicationTest;
import com.orderengine.user.model.dto.RegisterDataDto;
import com.orderengine.user.model.dto.UserAuthDataDto;
import com.orderengine.user.model.entity.User;
import com.orderengine.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserRegisterControllerTests extends SpringBootApplicationTest {

    private static final String USER_REGISTER_URL = "/user/user-service/register";

    @Test
    void shouldCreateNewUser() throws Exception {
        var registerDataDto = new RegisterDataDto("login1", "password1");
        mockmvc.perform(post(USER_REGISTER_URL)
            .content(objectMapper.writeValueAsString(registerDataDto))
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        User user = userRepository.findByLogin(registerDataDto.getLogin());
        Assertions.assertNotEquals(user.getPassword(), registerDataDto.getPassword());
    }

    @Test
    void shouldReturn400IfRegisterUserWithExistedLogin() throws Exception {
        var registerDataDto = new RegisterDataDto("login", "password1");
        mockmvc.perform(post(USER_REGISTER_URL)
            .content(objectMapper.writeValueAsString(registerDataDto))
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());

    }

}
