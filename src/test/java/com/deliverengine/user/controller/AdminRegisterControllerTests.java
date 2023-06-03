package com.deliverengine.user.controller;

import static com.deliverengine.user.util.Utils.randomString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
