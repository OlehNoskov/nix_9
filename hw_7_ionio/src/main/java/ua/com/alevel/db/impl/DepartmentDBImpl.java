package ua.com.alevel.db.impl;

import com.opencsv.CSVWriter;
import ua.com.alevel.db.DepartmentDB;
import ua.com.alevel.entity.Department;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class DepartmentDBImpl implements DepartmentDB {

    private static String FILE_PATH_DEPARTMENTS = "departments.csv";
    private static List<Department> departments;
    private static DepartmentDBImpl instance;

    private DepartmentDBImpl() {
        departments = new ArrayList<>();
    }

    public static DepartmentDBImpl getInstance() {
        if (instance == null) {
            instance = new DepartmentDBImpl();
        }
        return instance;
    }

    public String getFilePathDepartments() {
        return FILE_PATH_DEPARTMENTS;
    }

    public void create(Department department) {
        department.setId(generateId());
        departments.add(department);
        try {
            CSVWriter csvWriter = new CSVWriter(new FileWriter(FILE_PATH_DEPARTMENTS));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Department entity) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Department findByID(String id) {
        return null;
    }

    @Override
    public Collection<Department> findAll() {
        return null;
    }

    private static String generateId() {
        String id = UUID.randomUUID().toString();
        return id;
    }
}