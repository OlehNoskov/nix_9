package com.project.medicalanalize.persistence.repository.user;

import com.project.medicalanalize.persistence.entity.user.User;
import com.project.medicalanalize.persistence.repository.BaseRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository<U extends User> extends BaseRepository<U> {

    U findByEmail(String email);

    boolean existsByEmail(String email);
}