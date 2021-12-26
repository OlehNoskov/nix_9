package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.repository.GroupRepository;
import ua.com.alevel.service.GroupService;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class GroupServiceImpl implements GroupService {

    private final CrudRepositoryHelper<Group, GroupRepository> repositoryHelper;
    private final GroupRepository groupRepository;

    public GroupServiceImpl(CrudRepositoryHelper<Group, GroupRepository> repositoryHelper, GroupRepository groupRepository) {
        this.repositoryHelper = repositoryHelper;
        this.groupRepository = groupRepository;
    }

    @Override
    public void create(Group entity) {
        repositoryHelper.create(groupRepository, entity);
    }

    @Override
    public void update(Group entity) {
        repositoryHelper.update(groupRepository, entity);
    }

    @Override
    public void delete(Long id) {
        repositoryHelper.delete(groupRepository, id);
    }

    @Override
    public Optional<Group> findById(Long id) {
        return repositoryHelper.findById(groupRepository, id);
    }

    @Override
    public DataTableResponse<Group> findAll(DataTableRequest dataTableRequest) {
        return repositoryHelper.findAll(groupRepository, dataTableRequest);
    }

    @Override
    public Set<Group> findStudentByGroupId(Long id) {
        return Collections.emptySet();
    }
}