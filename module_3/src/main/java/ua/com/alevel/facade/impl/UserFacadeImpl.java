package ua.com.alevel.facade.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.persistence.entity.User;
import ua.com.alevel.service.UserService;
import ua.com.alevel.view.dto.request.UserRequestDto;
import ua.com.alevel.view.dto.response.UserResponseDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");

    public UserFacadeImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void create(UserRequestDto userRequestDto) {
        User user = new User();
        try {
            user.setFirstName(userRequestDto.getFirstName());
            user.setLastName(userRequestDto.getLastName());
            if (userRequestDto.getAge() < 0) {
                LOGGER_WARN.warn("Attempt to establish a negative age!");
                throw new RuntimeException("Age must be greater than zero!");
            }
            user.setAge(userRequestDto.getAge());
        } catch (NullPointerException ex) {
            throw new RuntimeException("Please fill in all fields!");
        }
        userService.create(user);
    }

    @Override
    public void update(UserRequestDto userRequestDto) {
        User user = new User();
        try {
            user.setId(userRequestDto.getId());
            user.setFirstName(userRequestDto.getFirstName());
            user.setLastName(userRequestDto.getLastName());
            user.setAge(userRequestDto.getAge());
            user.setUpdated(new Date());
        } catch (NullPointerException ex) {
            LOGGER_WARN.warn("Not all fields are filled!");
            throw new RuntimeException("Please fill in all fields!");
        }
        userService.update(user);
    }

    @Override
    public void delete(Long id) {
        userService.delete(id);
    }

    @Override
    public UserResponseDto findById(Long id) {
        return new UserResponseDto(userService.findById(id));
    }

    @Override
    public List<UserResponseDto> findAll() {
        List<User> userList = userService.findAll();
        List<UserResponseDto> allUsers = new ArrayList<>();
        for (User user : userList) {
            allUsers.add(new UserResponseDto(user));
        }
        return allUsers;
    }
}