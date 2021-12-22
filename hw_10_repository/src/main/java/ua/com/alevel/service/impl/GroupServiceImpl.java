package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.repository.GroupRepository;
import ua.com.alevel.service.GroupService;

import javax.transaction.Transactional;
import java.util.List;
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
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.NEVER)
    public void create(Group entity) {
        repositoryHelper.create(groupRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(Group entity) {
        repositoryHelper.update(groupRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(Long id) {
        repositoryHelper.delete(groupRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Group> findById(Long id) {
        return repositoryHelper.findById(groupRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Group> findAll(DataTableRequest dataTableRequest) {
        return repositoryHelper.findAll(groupRepository, dataTableRequest, Group.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Group> findByStudents(Set<Long> students) {
        return groupRepository.(students);
    }

    @Override
    public Set<Group> findByStudentsIds(Set<Long> students) {
        return groupRepository.findByStudentsIds(students);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Group> findByVisibleTrue() {
        return groupRepository.findByVisibleTrue();
    }
}