package com.deliverengine.user.service;

import com.deliverengine.core.enumeration.CourierStatus;
import com.deliverengine.core.enumeration.RolesConstants;
import com.deliverengine.user.mapper.UserMapper;
import com.deliverengine.user.model.dto.CourierDto;
import com.deliverengine.user.model.dto.ExtendedUserDto;
import com.deliverengine.user.model.dto.UpdateCourierStatusRequestDto;
import com.deliverengine.user.model.entity.User;
import com.deliverengine.user.repository.UserRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper userMapper;

    public UserService(UserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    public Optional<User> findUserByLogin(String login) {
        return Optional.ofNullable(repository.findByLogin(login));
    }

    public User save(User user) {
        return repository.saveAndFlush(user);
    }

    public List<CourierDto> findAllByCourierStatusIs(CourierStatus courierStatus) {
        return userMapper.toDto(repository.findAllByCourierStatusIs(courierStatus));
    }

    public CourierDto findCourierById(Long id) {
        Optional<User> optionalCourier = repository.findOneByIdAndRoleName(id, RolesConstants.ROLE_COURIER.name());
        return optionalCourier.map(userMapper::toDto).orElse(null);
    }

    public Boolean updateCourierStatus(UpdateCourierStatusRequestDto requestDto) {
        User user = repository.findOneByIdAndRoleName(requestDto.getId(), RolesConstants.ROLE_COURIER.name()).orElseThrow();
        user.setCourierStatus(requestDto.getCourierStatus());
        repository.save(user);
        return true;
    }

    public ExtendedUserDto findUserById(Long id) {
        return repository.findOneByIdAndRoleName(id, RolesConstants.ROLE_USER.name()).map(userMapper::toExtendedDto).orElse(null);
    }

    public Boolean updateUserAccount(Long id, BigDecimal accountValue) {
        User user = repository.findOneByIdAndRoleName(id, RolesConstants.ROLE_USER.name()).orElseThrow();
        user.setAccountBalance(accountValue);
        repository.save(user);
        return true;
    }

    public List<CourierDto> findAll() {
        return userMapper.toDto(repository.findAllByRoleName(RolesConstants.ROLE_COURIER.name()).orElseGet(ArrayList::new));
    }

    public CourierDto findCourierByLogin(String login) {
        return repository.findOneByLoginAndRoleName(login, RolesConstants.ROLE_COURIER.name()).map(userMapper::toDto).orElse(null);
    }
}
