package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.JpaConfig;
import ua.com.alevel.persistence.dao.StudentDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.BaseEntity;

@Service
public class StudentDaoImpl implements StudentDao {
    private final JpaConfig jpaConfig;

    public StudentDaoImpl(JpaConfig jpaConfig) {
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