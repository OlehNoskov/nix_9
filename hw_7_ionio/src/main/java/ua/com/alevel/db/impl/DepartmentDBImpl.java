package ua.com.alevel.db.impl;

import ua.com.alevel.CustomCSVRead;
import ua.com.alevel.CustomCSVWrite;
import ua.com.alevel.db.DepartmentDB;
import ua.com.alevel.entity.Department;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class DepartmentDBImpl implements DepartmentDB {

    private static final String FILE_PATH_DEPARTMENTS = "departments.csv";
    private static List<String[]> departments;
    private static DepartmentDBImpl instance;
    public static String[] header = {"id", "title_department"};

    public DepartmentDBImpl() {
        departments = new ArrayList<>();
    }

    public static DepartmentDBImpl getInstance() {
        if (instance == null) {
            instance = new DepartmentDBImpl();
        }
        return instance;
    }

    public static String getPathFileDepartments() {
        return FILE_PATH_DEPARTMENTS;
    }

    public static String[] getHeaderCSVFile() {
        return header;
    }

    public void create(Department department) {
        department.setId(generateId());

        departments.add(Department.parserToStringDepartment(department));
        CustomCSVWrite.writeToCSVFile(departments, DepartmentDBImpl.getPathFileDepartments(), true);
        departments.clear();
    }

    @Override
    public void update(Department department) {
        Department updateDepartment = findByID(department.getId());
        updateDepartment.setDepartmentName(department.getDepartmentName());

        departments.add(Department.parserToStringDepartment(updateDepartment));
        String[] departmentBase = CustomCSVRead
                .search(CustomCSVRead.readCSVFile(DepartmentDBImpl
                        .getPathFileDepartments()), department.getId());
        String idDataBase = departmentBase[0];
        List<String[]> temp = CustomCSVRead.readCSVFile(DepartmentDBImpl.getPathFileDepartments());
        if (department.getId().equals(idDataBase)) {
            for (int i = 0; i < temp.size(); i++) {
                for (int b = 0; b < temp.get(i).length; b++) {
                    if (idDataBase.equals(temp.get(i)[0])) {
                        temp.remove(temp.get(i)[b]);
                    } else {
                        departments.add(temp.get(i));
                    }
                }
            }
        }
//        departments.addAll(CustomCSVRead.readCSVFile(DepartmentDBImpl.getPathFileDepartments()));
        CustomCSVWrite.writeToCSVFile(departments, DepartmentDBImpl.getPathFileDepartments(), false);
    }

    @Override
    public void delete(String id) {
        List<String[]> departmentList = CustomCSVRead.readCSVFile(getPathFileDepartments());

        for (String[] str : departmentList) {
            for (int i = 0; i < str.length; i++) {
                if (id.equals(str[0])) {
                    str = null;
                    CustomCSVWrite.writeToCSVFile(departmentList, getPathFileDepartments(), false);
                } else {
                    System.out.println("Департамент не найдено!");
                }
            }
        }
        throw new RuntimeException("Департамента с id = " + id + "не найдено!");
    }

    @Override
    public Department findByID(String id) {

        List<String[]> departmentList = CustomCSVRead.readCSVFile(getPathFileDepartments());
        Department department = new Department();
        for (String[] str : departmentList) {
            if ((str[0]).equals(id)) {
                department.setId(str[0]);
                department.setDepartmentName(str[1]);
                return department;
            }
        }
        return department;
    }

    @Override
    public List<Department> findAll() {

        List<String[]> listCsvFile = CustomCSVRead.readCSVFile(getPathFileDepartments());
        List<Department> departmentList = new ArrayList<>();

        for (String[] str : listCsvFile) {
            departmentList.add(Department.parserStringToDepartment(str));
            return departmentList;
        }
        return departmentList;
    }

    private static String generateId() {
        String id = UUID.randomUUID().toString();
        return id;
    }

    private static String[] addHeaderCSVFile() {
        return header;
    }

    public static List<String[]> getDepartments() {
        return departments;
    }
}