package com.project.medicalanalize.service.impl;

import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.BaseEntity;
import com.project.medicalanalize.service.BaseCrudService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorServiceImpl implements BaseCrudService {
    @Override
    public void create(BaseEntity entity) {

    }

    @Override
    public void update(BaseEntity entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional findById(Long id) {
        return Optional.empty();
    }

    @Override
    public DataTableResponse findAll(DataTableRequest request) {
        return null;
    }
}