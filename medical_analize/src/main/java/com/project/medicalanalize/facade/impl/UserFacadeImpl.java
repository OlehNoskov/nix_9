package com.project.medicalanalize.facade.impl;

import com.project.medicalanalize.facade.UserFacade;
import com.project.medicalanalize.persistence.entity.user.User;
import com.project.medicalanalize.service.UserService;
import com.project.medicalanalize.util.SecurityUtil;

import org.springframework.stereotype.Service;

@Service
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    public UserFacadeImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User getCurrentUser() {
        return userService.getCurrentUserByEmail(SecurityUtil.getUsername());
    }
}
