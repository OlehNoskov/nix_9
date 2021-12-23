package com.project.medical_analize.service;

import com.project.medical_analize.persistence.datatable.DataTableRequest;
import com.project.medical_analize.persistence.datatable.DataTableResponse;
import com.project.medical_analize.persistence.entity.BaseEntity;

import java.util.Optional;

public interface BaseCrudService <E extends BaseEntity>{

    void create(E entity);
    void update(E entity);
    void delete(Long id);
    Optional<E> findById(Long id);
    DataTableResponse<E> findAll(DataTableRequest request);
}