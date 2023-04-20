package com.orderengine.user.controller;

import com.orderengine.user.SpringBootApplicationTest;
import com.orderengine.user.model.dto.JWTToken;
import com.orderengine.user.model.dto.RegisterDataDto;
import com.orderengine.user.model.dto.UserAuthDataDto;
import com.orderengine.user.model.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.Charset;

import static com.orderengine.user.Utils.randomString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UserAuthenticationControllerTests extends SpringBootApplicationTest {
    private static final String USER_REGISTER_URL = "/user/user-service/register";
    private static final String USER_AUTHENTICATE_URL = "/user/user-service/authenticate";

    @Test
    void shouldRegisterUserAndReturnAuthenticatedUserToken() throws Exception {
        var registerDataDto = new RegisterDataDto(randomString(), randomString());
        mockmvc.perform(post(USER_REGISTER_URL)
            .content(objectMapper.writeValueAsString(registerDataDto))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());

        User user = userRepository.findByLogin(registerDataDto.getLogin());
        Assertions.assertNotEquals(user.getPassword(), registerDataDto.getPassword());

        UserAuthDataDto authDataDto = new UserAuthDataDto(registerDataDto.getLogin(), registerDataDto.getPassword());
        MvcResult mvcResult = mockmvc.perform(post(USER_AUTHENTICATE_URL)
            .content(objectMapper.writeValueAsString(authDataDto))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk()).andReturn();

        JWTToken token = objectMapper.readValue(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8")), JWTToken.class);
        Assertions.assertTrue(StringUtils.isNotEmpty(token.getJwt()));
    }


}
