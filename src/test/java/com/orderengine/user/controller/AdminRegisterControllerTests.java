package com.orderengine.user.controller;

import com.orderengine.user.model.dto.RegisterDataDto;
import com.orderengine.user.model.dto.UserDto;
import com.orderengine.user.model.enumeration.AuthoritiesConstants;
import com.orderengine.user.model.enumeration.RolesConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.Charset;
import java.util.List;

import static com.orderengine.user.util.Utils.randomString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminRegisterControllerTests extends AbstractRegisterControllerTests {

    private static final String ADMIN_REGISTER_URL = "/admin/user-service/register";

    public AdminRegisterControllerTests() {
        super(ADMIN_REGISTER_URL);
    }

    @Override
    protected Boolean needToAuthenticateUserBeforeRequest() {
        return true;
    }
}
