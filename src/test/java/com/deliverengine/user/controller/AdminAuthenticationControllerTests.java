package com.deliverengine.user.controller;

public class AdminAuthenticationControllerTests extends AbstractAuthenticationControllerTests {

    public AdminAuthenticationControllerTests() {
        super("/admin/user-service/register", "/admin/user-service/authenticate");
    }

    @Override
    protected Boolean needToAuthenticateUserBeforeRequest() {
        return true;
    }
}
