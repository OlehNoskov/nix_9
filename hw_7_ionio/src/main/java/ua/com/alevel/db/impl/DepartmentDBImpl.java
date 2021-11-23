package ua.com.alevel.db.impl;

import com.opencsv.CSVWriter;
import ua.com.alevel.entity.Department;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DepartmentDBImpl {

    private static String FILE_PATH_DEPARTMENTS = "hw_7_ionio/departments.csv";
    private List<Department> departments;
    private static DepartmentDBImpl instance;

    private DepartmentDBImpl(){
        departments = new ArrayList<>();
    }

    public static DepartmentDBImpl getInstance(){
        if (instance == null) {
            instance = new DepartmentDBImpl();
        }
        return instance;
    }

    public String getFilePathDepartments(){
        return FILE_PATH_DEPARTMENTS;
    }

    public void create(Department department){
        department.setId(generateId());
        try(CSVWriter csvWriter = new CSVWriter(new FileWriter(FILE_PATH_DEPARTMENTS))) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateId(){
        String id = UUID.randomUUID().toString();
        return id;
    }
}