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
    public void create(Group group) {
        groupDao.create(group);
    }

    @Override
    public void update(Group group) {
        groupDao.update(group);
    }

    @Override
    public void delete(Long id) {
        if(groupDao.existById(id)){
            groupDao.delete(id);
        }
    }

    @Override
    public Group findById(Long id) {
        return groupDao.findById(id);
    }

    @Override
    public DataTableResponse<Group> findAll(DataTableRequest request) {
        DataTableResponse<Group> dataTableResponse = groupDao.findAll(request);
        long count = groupDao.count();
        dataTableResponse.setItemsSize(count);
        return dataTableResponse;
    }
}