package ua.com.alevel.service.impl;

import ua.com.alevel.persistence.dao.impl.DepartmentDaoImpl;
import ua.com.alevel.persistence.entity.Department;
import ua.com.alevel.service.DepartmentService;

import java.io.IOException;
import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDaoImpl departmentDao = new DepartmentDaoImpl();

    @Override
    public void create(Department entity) {
        try {
            departmentDao.create(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Department entity) {
        departmentDao.update(entity);
    }

    @Override
    public void delete(String id) {
        departmentDao.delete(id);
    }

    @Override
    public Department findByID(String id) {
        return departmentDao.findByID(id);
    }

    @Override
    public List<Department> findByAll() {
        return departmentDao.findAll();
    }

    public static void findAllEmployeeFromDepartment(String idDepartment){
         DepartmentDaoImpl.findAllEmployeeFromDepartment(idDepartment);
    }
}