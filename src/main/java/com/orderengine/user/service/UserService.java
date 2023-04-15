package com.orderengine.user.service;

import com.orderengine.user.model.entity.User;
import com.orderengine.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Optional<User> findUserByLogin(String login) {
        return Optional.ofNullable(repository.findByLogin(login));
    }

    public User save(User user) {
        return repository.saveAndFlush(user);
    }
}
