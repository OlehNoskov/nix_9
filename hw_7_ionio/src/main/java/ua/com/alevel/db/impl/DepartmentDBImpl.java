package ua.com.alevel.db.impl;

import ua.com.alevel.CustomCSVRead;
import ua.com.alevel.CustomCSVWrite;
import ua.com.alevel.controller.BaseController;
import ua.com.alevel.controller.impl.BaseControllerImpl;
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
        System.out.println("Департамент "+department.getDepartmentName()+" успешно создан!");
        departments.clear();
    }

    @Override
    public void update(Department department) {
        Department updateDepartment = findByID(department.getId());
        updateDepartment.setDepartmentName(department.getDepartmentName());

        List<String[]> temp = CustomCSVRead.readCSVFile(DepartmentDBImpl.getPathFileDepartments());

            for (int i = 0; i < temp.size(); i++) {
                for (int b = 1; b < temp.get(i).length; b++) {
                    if (temp.get(i)[0].equals(department.getId())) {
                        temp.get(i)[1] = department.getDepartmentName();
                        departments.add(temp.get(i));
                    } else {
                        departments.add(temp.get(i));
                    }
                }
            }
        CustomCSVWrite.writeToCSVFile(departments, DepartmentDBImpl.getPathFileDepartments(), false);
        System.out.println("Данные успешно обновлены!");
        departments.clear();
    }

    @Override
    public void delete(String id) {
        List<String[]> removeDepartment = new ArrayList<>();
        List<String[]> departmentList = new ArrayList<>(CustomCSVRead.readCSVFile(getPathFileDepartments()));

        for (String[] str : departmentList) {
            for (int i = 0; i < str.length; i++) {
                if (id.equals(str[0])) {
                    removeDepartment.add(str);
//                    System.out.println("Департамент "+str[1]+" был успешно удален!");
                }
            }
        }
        System.out.println("Департамент "+ removeDepartment.get(0)[1] +" был успешно удален!");
        departmentList.removeAll(removeDepartment);
        CustomCSVWrite.writeToCSVFile(departmentList, getPathFileDepartments(), false);
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

        for (int i = 1; i < listCsvFile.size(); i++) {
            departmentList.add(Department.parserStringToDepartment(listCsvFile.get(i)));
        }
            for(int i=0; i<departmentList.size(); i++){
                System.out.println(departmentList.get(i).toString());
            }

        return departmentList;
    }

    private static String generateId() {
        String id = UUID.randomUUID().toString();
        return id;
    }

//    private static String[] addHeaderCSVFile() {
//        return header;
//    }
//
//    public static List<String[]> getDepartments() {
//        return departments;
//    }
}