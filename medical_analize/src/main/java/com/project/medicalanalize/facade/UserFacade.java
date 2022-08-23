package com.project.medicalanalize.facade;

import com.project.medicalanalize.persistence.entity.user.User;

public interface UserFacade {

    User getCurrentUser();
}