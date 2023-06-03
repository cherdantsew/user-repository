package com.deliverengine.user.controller;

public class CourierRegisterControllerTests extends AbstractRegisterControllerTests {

    private static final String COURIER_REGISTER_URL = "/courier/user-service/register";

    public CourierRegisterControllerTests() {
        super(COURIER_REGISTER_URL);
    }

    @Override
    protected Boolean needToAuthenticateUserBeforeRequest() {
        return false;
    }
}
