package com.project.medicalanalize.persistence.crud.impl;

import com.project.medicalanalize.persistence.crud.CrudRepositoryHelper;
import com.project.medicalanalize.persistence.datatable.DataTableRequest;
import com.project.medicalanalize.persistence.datatable.DataTableResponse;
import com.project.medicalanalize.persistence.entity.BaseEntity;
import com.project.medicalanalize.persistence.repository.BaseRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CrudRepositoryHelperImpl <
        E extends BaseEntity,
        R extends BaseRepository<E>>
        implements CrudRepositoryHelper<E,R> {

    @Override
    public void create(R repository, E entity) {
        repository.save(entity);
    }

    @Override
    public Long createAndFind(R repository, E entity) {
        return repository.save(entity).getId();
    }

    @Override
    public void update(R repository, E entity) {
        checkExist(repository, entity.getId());
        repository.save(entity);
    }

    @Override
    public void delete(R repository, Long id) {
        checkExist(repository, id);
        repository.deleteById(id);
    }

    @Override
    public Optional<E> findById(R repository, Long id) {
        checkExist(repository, id);
        return repository.findById(id);
    }

    @Override
    public DataTableResponse<E> findAll(R repository, DataTableRequest request) {
        Sort sort = request.getOrder().equals("desc")
                ? Sort.by(request.getSort()).descending()
                : Sort.by(request.getSort()).ascending();
        Page<E> entityPage = repository.findAll(
                PageRequest.of(request.getPage() - 1, request.getSize(), sort));
        DataTableResponse<E> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setCurrentPage(request.getPage());
        dataTableResponse.setPageSize(request.getSize());
        dataTableResponse.setOrder(request.getOrder());
        dataTableResponse.setSort(request.getSort());
        dataTableResponse.setItemsSize(entityPage.getTotalElements());
        dataTableResponse.setTotalPages(entityPage.getTotalPages());
        dataTableResponse.setItems(entityPage.getContent());
        return dataTableResponse;
    }

    private void checkExist(R repository, Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("This entity is not found!");
        }
    }
}