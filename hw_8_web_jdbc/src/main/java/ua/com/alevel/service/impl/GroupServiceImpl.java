package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.GroupDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupDao groupDao;

    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public void create(Group entity) {

    }

    @Override
    public void update(Group entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Group findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<Group> findAll(DataTableRequest request) {
        return groupDao.findAll(request);
    }
}