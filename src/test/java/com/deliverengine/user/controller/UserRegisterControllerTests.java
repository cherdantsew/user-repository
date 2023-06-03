package com.deliverengine.user.controller;

public class UserRegisterControllerTests extends AbstractRegisterControllerTests {

    private static final String USER_REGISTER_URL = "/user/user-service/register";

    public UserRegisterControllerTests() {
        super(USER_REGISTER_URL);
    }

    @Override
    protected Boolean needToAuthenticateUserBeforeRequest() {
        return false;
    }
}
