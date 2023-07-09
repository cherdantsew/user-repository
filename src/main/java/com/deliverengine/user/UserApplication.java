package com.deliverengine.user;

import com.deliverengine.core.utils.DefaultProfileUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
    basePackages = {"com.deliverengine"})
public class UserApplication {

    @PostConstruct
    public void mett(){

        System.out.println("_________________________________________");
        System.out.println("_________________________________________");
        System.out.println("_________________________________________");
        System.out.println("_________________________________________");
        System.out.println("_________________________________________");
        System.out.println("_________________________________________");
        System.out.println("_________________________________________");
        System.out.println("_________________________________________");
    }
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UserApplication.class);
        DefaultProfileUtil.addDefaultProfile(app);
        app.run(args);


    }
}
