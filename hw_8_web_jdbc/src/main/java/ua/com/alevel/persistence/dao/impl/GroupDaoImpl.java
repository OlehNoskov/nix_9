package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.JpaConfig;
import ua.com.alevel.persistence.dao.GroupDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.BaseEntity;

@Service
public class GroupDaoImpl implements GroupDao {

    private final JpaConfig jpaConfig;

    public GroupDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(BaseEntity entity) {

    }

    @Override
    public void update(BaseEntity entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public BaseEntity findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
}