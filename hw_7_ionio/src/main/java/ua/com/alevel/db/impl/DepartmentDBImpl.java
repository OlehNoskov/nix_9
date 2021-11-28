//package ua.com.alevel.db.impl;
//
//import com.opencsv.CSVWriter;
//import ua.com.alevel.db.DepartmentDB;
//import ua.com.alevel.entity.Department;
//
//import java.io.FileWriter;
//import java.io.IOException;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//public class DepartmentDBImpl implements DepartmentDB {
//
//    private static final String FILE_PATH_DEPARTMENTS = "departments.csv";
//    private static List<Department> departments;
//    private static DepartmentDBImpl instance;
//    private static String[] header = { "name",  "lastName", "age"};
//
//    private DepartmentDBImpl() {
//        departments = new ArrayList<>();
//    }
//
//    public static DepartmentDBImpl getInstance() {
//        if (instance == null) {
//            instance = new DepartmentDBImpl();
//        }
//        return instance;
//    }
//
//    public static String getPathFileDepartments() {
//        return FILE_PATH_DEPARTMENTS;
//    }
//
//    public static String[] getHeaderCSVFile(){
//        return header;
//    }
//
////    public void create(Department department) {
////        if(departments.size()==0){
////            addHeaderCSVFile();
////        }else {
////            department.setId(generateId());
////            departments.add(department);
////            try {
////                CSVWriter csvWriter = new CSVWriter(new FileWriter(getPathFileDepartments(), true));
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////        }
////    }
//
//    @Override
//    public void update(Department entity) {
//        Department current = findByID(entity.getId());
//        current.setDepartmentName(entity.getDepartmentName());
//    }
//
//    @Override
//    public void delete(String id) {
//
//    }
//
//    @Override
//    public Department findByID(String id) {
//        for (Department department : departments) {
//            if (department.getId().equals(id)) {
//                return department;
//            }
//        }
//        throw new RuntimeException("Департамента с id = " + id+"не найдено!");
//    }
//
//    @Override
//    public List<Department> findAll() {
//        return null;
//    }
//
//    private static String generateId() {
//        String id = UUID.randomUUID().toString();
//        return id;
//    }
//
////    private void writeToCSV(String fileName, Department department) {
////        try {
////            CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName));
////            csvWriter.writeAll(departments);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
//
//    private static void addHeaderCSVFile(){
//        String[] header = {"id","departmentName"};
//    }
//}