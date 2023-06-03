package com.deliverengine.user.service;

import com.deliverengine.core.enumeration.AuthoritiesConstants;
import com.deliverengine.user.model.entity.Role;
import com.deliverengine.user.model.entity.User;
import com.deliverengine.user.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);

        String lowercaseLogin = login.trim();
        Optional<User> userByLoginFromDatabase = Optional.ofNullable(userRepository.findByLogin(lowercaseLogin));
        return userByLoginFromDatabase.map(user -> createSpringSecurityUser(lowercaseLogin, user))
            .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, User user) {
        List<AuthoritiesConstants> authorities = user.getRoles().stream()
            .map(Role::getAuthorities)
            .flatMap(it -> Stream.of(it.split(",")))
            .map(AuthoritiesConstants::valueOf)
            .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
            user.getLogin(),
            user.getPassword(),
            authorities
        );
    }
}