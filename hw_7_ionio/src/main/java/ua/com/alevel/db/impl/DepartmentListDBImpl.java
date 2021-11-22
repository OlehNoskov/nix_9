package ua.com.alevel.db.impl;

import ua.com.alevel.entity.Department;

import java.util.List;

public class DepartmentListDBImpl {
    private static String FILE_PATH_DEPARTMENTS = "departments.csv";
    private List<Department> departments;
    private static DepartmentListDBImpl instance;
}