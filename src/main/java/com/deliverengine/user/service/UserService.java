package com.deliverengine.user.service;

import com.deliverengine.user.repository.UserRepository;
import com.deliverengine.user.model.entity.User;
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
