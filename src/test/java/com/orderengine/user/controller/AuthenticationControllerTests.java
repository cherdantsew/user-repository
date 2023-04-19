package com.orderengine.user.controller;

import com.orderengine.user.SpringBootApplicationTest;
import com.orderengine.user.model.dto.RegisterDataDto;
import com.orderengine.user.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthenticationControllerTests extends SpringBootApplicationTest {

    @Test
    public void shouldAuthenticateAdminAndReturnToken() throws JsonProcessingException {
            var registerDataDto = new RegisterDataDto("login1", "password1");
//            mockmvc.perform(post("/user/register")
//                .content(objectMapper.writeValueAsString(registerDataDto))
//                .contentType(MediaType.APPLICATION_JSON)
//            ).andExpect(status().isOk());

            User user = userRepository.findByLogin(registerDataDto.getLogin());
            Assertions.assertEquals(user.getPassword(), registerDataDto.getPassword());
    }
}
