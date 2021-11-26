package ua.com.alevel.db.impl;

import ua.com.alevel.db.EmployeeDB;
import ua.com.alevel.entity.Department;
import ua.com.alevel.entity.Employee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EmployeeDBImpl implements EmployeeDB {

    private static String FILE_PATH_DEPARTMENTS = "departments.csv";
    private static List<Employee> employees;
    private static EmployeeDBImpl instance;

    private EmployeeDBImpl() {
        employees = new ArrayList<>();
    }

    public static EmployeeDBImpl getInstance() {
        if (instance == null) {
            instance = new EmployeeDBImpl();
        }
        return instance;
    }

    public String getFilePathDepartments() {
        return FILE_PATH_DEPARTMENTS;
    }

    @Override
    public void create(Employee entity) {

    }

    @Override
    public void update(Employee entity) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Employee findByID(String id) {
        return null;
    }

    @Override
    public Collection<Employee> findAll() {
        return null;
    }
}
