package com.deliverengine.user.controller;

public class UserAuthenticationControllerTests extends AbstractAuthenticationControllerTests {

    public UserAuthenticationControllerTests() {
        super("/user/user-service/register", "/user/user-service/authenticate");
    }

    @Override
    protected Boolean needToAuthenticateUserBeforeRequest() {
        return false;
    }
}
