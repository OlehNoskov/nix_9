package com.project.medicalanalize.service;

import com.project.medicalanalize.persistence.entity.user.User;

public interface UserService {

    User getCurrentUserByEmail(String email);
}
