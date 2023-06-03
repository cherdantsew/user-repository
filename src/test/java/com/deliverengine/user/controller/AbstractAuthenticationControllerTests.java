package com.deliverengine.user.controller;

import com.deliverengine.core.enumeration.AuthoritiesConstants;
import com.deliverengine.core.enumeration.RolesConstants;
import com.deliverengine.user.SpringBootApplicationTest;
import com.deliverengine.user.model.dto.JWTToken;
import com.deliverengine.user.model.dto.RegisterDataDto;
import com.deliverengine.user.model.dto.UserAuthDataDto;
import com.deliverengine.user.model.entity.User;
import java.nio.charset.Charset;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static com.deliverengine.user.util.Utils.randomString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public abstract class AbstractAuthenticationControllerTests extends SpringBootApplicationTest {

    private String regUrl;
    private String authUrl;

    public AbstractAuthenticationControllerTests(String regUrl, String authUrl) {
        this.regUrl = regUrl;
        this.authUrl = authUrl;
    }

    @Test
    void shouldAuthenticateUserAndReturnToken() throws Exception {
        if (needToAuthenticateUserBeforeRequest()) {
            authenticateAdmin();
        }
        var registerDataDto = new RegisterDataDto(randomString(), randomString());
        mockmvc.perform(post(regUrl)
            .content(objectMapper.writeValueAsString(registerDataDto))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk());

        User user = userRepository.findByLogin(registerDataDto.getLogin());
        Assertions.assertNotEquals(user.getPassword(), registerDataDto.getPassword());

        UserAuthDataDto authDataDto = new UserAuthDataDto(registerDataDto.getLogin(), registerDataDto.getPassword());

        MvcResult mvcResult = mockmvc.perform(post(authUrl)
            .content(objectMapper.writeValueAsString(authDataDto))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk()).andReturn();

        JWTToken token = objectMapper.readValue(mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8")), JWTToken.class);
        Assertions.assertTrue(StringUtils.isNotEmpty(token.getJwt()));
    }

    protected abstract Boolean needToAuthenticateUserBeforeRequest();

    private void authenticateAdmin(){
        super.authenticateAndReturnToken(randomString(), RolesConstants.ROLE_ADMIN, List.of(AuthoritiesConstants.CURRENT_USER));
    }
}
