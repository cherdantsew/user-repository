package com.orderengine.user.controller;

public class AdminAuthenticationControllerTests extends AbstractAuthenticationControllerTests {

    public AdminAuthenticationControllerTests() {
        super("/admin/user-service/register", "/admin/user-service/authenticate");
    }

    @Override
    protected Boolean needToAuthenticateUserBeforeRequest() {
        return true;
    }
}
