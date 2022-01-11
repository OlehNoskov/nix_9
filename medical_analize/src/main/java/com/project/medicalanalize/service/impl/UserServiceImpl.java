package com.project.medicalanalize.service.impl;

import com.project.medicalanalize.persistence.entity.user.User;
import com.project.medicalanalize.persistence.repository.user.UserRepository;
import com.project.medicalanalize.service.UserService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository<User> userRepository;

    public UserServiceImpl(UserRepository<User> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public User getCurrentUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}