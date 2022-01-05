package com.project.medical_analize.service.impl;

import com.project.medical_analize.persistence.datatable.DataTableRequest;
import com.project.medical_analize.persistence.datatable.DataTableResponse;
import com.project.medical_analize.persistence.entity.BaseEntity;
import com.project.medical_analize.service.BaseCrudService;
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