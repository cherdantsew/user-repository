package com.orderengine.user.controller;

import com.orderengine.user.SpringBootApplicationTest;
import com.orderengine.user.model.dto.RegisterDataDto;
import com.orderengine.user.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CourierRegisterControllerTests extends SpringBootApplicationTest {

    private static final String COURIER_REGISTER_URL = "/courier/user-service/register";

    @Test
    void shouldCreateNewCourier() throws Exception {
        var registerDataDto = new RegisterDataDto("login1", "password1");
        mockmvc.perform(post(COURIER_REGISTER_URL)
            .content(objectMapper.writeValueAsString(registerDataDto))
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        User user = userRepository.findByLogin(registerDataDto.getLogin());
        Assertions.assertEquals(user.getPassword(), registerDataDto.getPassword());
    }

    @Test
    void shouldReturnBadRequestIfCreateCourierWithAdminLogin() throws Exception {
        var registerDataDto = new RegisterDataDto("login", "password");
        mockmvc.perform(post(COURIER_REGISTER_URL)
            .content(objectMapper.writeValueAsString(registerDataDto))
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }
}
