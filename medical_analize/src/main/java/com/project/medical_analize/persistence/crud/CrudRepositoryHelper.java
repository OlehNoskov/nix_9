package com.project.medical_analize.persistence.crud;

import com.project.medical_analize.persistence.datatable.DataTableRequest;
import com.project.medical_analize.persistence.datatable.DataTableResponse;
import com.project.medical_analize.persistence.entity.BaseEntity;
import com.project.medical_analize.persistence.repository.BaseRepository;

import java.util.Optional;

public interface CrudRepositoryHelper <E extends BaseEntity, R extends BaseRepository<E>>{
    void create(R repository, E entity);
    void update(R repository, E entity);
    void delete(R repository, Long id);
    Optional<E> findById(R repository, Long id);
    DataTableResponse<E> findAll(R repository, DataTableRequest request);
}