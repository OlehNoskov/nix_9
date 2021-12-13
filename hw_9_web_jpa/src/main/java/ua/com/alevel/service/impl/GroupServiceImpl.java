package ua.com.alevel.service.impl;

import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.dao.GroupDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.service.GroupService;

public class GroupServiceImpl implements GroupService {

    private final GroupDao groupDao;

    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public void create(Group entity) {
        groupDao.create(entity);
    }

    @Override
    public void update(Group entity) {
        if (!groupDao.existById(entity.getId())) {
            throw new EntityNotFoundException("group not found");
        }
        groupDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        if (!groupDao.existById(id)) {
            throw new EntityNotFoundException("group not found");
        }
        groupDao.delete(id);
    }

    @Override
    public Group findById(Long id) {
        if (!groupDao.existById(id)) {
            throw new EntityNotFoundException("group not found");
        }
     return groupDao.findById(id);
    }

    @Override
    public DataTableResponse<Group> findAll(DataTableRequest request) {
        return groupDao.findAll(request);
    }
}