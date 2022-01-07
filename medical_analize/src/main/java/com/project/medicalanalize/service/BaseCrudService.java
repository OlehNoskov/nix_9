package com.project.medicalanalize.service;

import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.BaseEntity;

import java.util.Optional;

public interface BaseCrudService<E extends BaseEntity> {

    void create(E entity);
    void update(E entity);
    void delete(Long id);
    Optional<E> findById(Long id);
    DataTableResponse<E> findAll(DataTableRequest request);
}