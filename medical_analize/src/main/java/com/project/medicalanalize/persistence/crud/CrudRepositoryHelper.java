package com.project.medicalanalize.persistence.crud;

import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.BaseEntity;
import com.project.medicalanalize.persistence.repository.BaseRepository;

import java.util.Optional;

public interface CrudRepositoryHelper <E extends BaseEntity, R extends BaseRepository<E>>{
    void create(R repository, E entity);
    void update(R repository, E entity);
    void delete(R repository, Long id);
    Optional<E> findById(R repository, Long id);
    DataTableResponse<E> findAll(R repository, DataTableRequest request);
}