package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.DepartmentDao;
import ua.com.alevel.db.impl.DepartmentDBImpl;
import ua.com.alevel.entity.Department;

import java.io.IOException;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {

    private final DepartmentDBImpl departmentDB = DepartmentDBImpl.getInstance();

    @Override
    public void create(Department entity) throws IOException {
        departmentDB.create(entity);
    }

    @Override
    public void update(Department entity) {
        departmentDB.update(entity);
    }

    @Override
    public void delete(String id) {
        departmentDB.delete(id);
    }

    @Override
    public Department findByID(String id) {
        return departmentDB.findByID(id);
    }

    @Override
    public List<Department> findAll() {
        return departmentDB.findAll();
    }
}