package com.orderengine.user;

import com.orderengine.user.utils.DefaultProfileUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
    basePackages = {"com.orderengine"})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UserApplication.class);
        DefaultProfileUtil.addDefaultProfile(app);
        app.run(args);
    }
}
