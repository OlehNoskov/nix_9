package ua.com.alevel.service.impl;

import ua.com.alevel.dao.EmployeeDao;
import ua.com.alevel.dao.impl.EmployeeDaoImpl;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.service.EmployeeService;

import java.util.Collection;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDao employeeDao = new EmployeeDaoImpl();

    @Override
    public void create(Employee entity) {
        employeeDao.create(entity);
    }

    @Override
    public void update(Employee entity) {
        employeeDao.update(entity);
    }

    @Override
    public void delete(String id) {
        employeeDao.delete(id);
    }

    @Override
    public Employee findByID(String id) {
        return employeeDao.findByID(id);
    }

    @Override
    public Collection<Employee> findByAll() {
        return employeeDao.findAll();
    }
}