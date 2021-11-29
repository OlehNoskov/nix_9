package ua.com.alevel.db.impl;

import ua.com.alevel.CustomCSVRead;
import ua.com.alevel.CustomCSVWrite;
import ua.com.alevel.db.EmployeeDB;
import ua.com.alevel.entity.Department;
import ua.com.alevel.entity.Employee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class EmployeeDBImpl implements EmployeeDB {

    private static final String FILE_PATH_EMPLOYEE = "employees.csv";
    private static List<String[]> employees;
    private static EmployeeDBImpl instance;
    private static String[] header = {"id","name", "lastName","age"};

    private EmployeeDBImpl() {
        employees = new ArrayList<>();
    }

    public static EmployeeDBImpl getInstance() {
        if (instance == null) {
            instance = new EmployeeDBImpl();
        }
        return instance;
    }

    public static String getFilePathEmployees() {
        return FILE_PATH_EMPLOYEE;
    }

    public static String[] getHeaderCSVFile(){
        return header;
    }

    @Override
    public void create(Employee employee) {
        employee.setId(generateId());
        employees.add(Employee.parserToStringEmployee(employee));
    }

    @Override
    public void update(Employee employee) {
        Employee updateEmployee = findByID(employee.getId());
        updateEmployee.setNameEmployee(employee.getNameEmployee());
        updateEmployee.setLastNameEmployee(employee.getLastNameEmployee());
        updateEmployee.setAge(employee.getAge());
    }

    @Override
    public void delete(String id) {
        List<String[]> employeesList = CustomCSVRead.readCSVFile(getFilePathEmployees());

        for (String[] str : employeesList) {
            for (int i = 0; i < str.length; i++) {
                if (id.equals(str[0])) {
                    str = null;
                    CustomCSVWrite.writeToCSVFile(employeesList, getFilePathEmployees(), false);
                } else {
                    System.out.println("Сотрудника не найдено!");
                }
            }
        }
        throw new RuntimeException("Сотрудника с id = " + id + "не найдено!");
    }

    @Override
    public Employee findByID(String id) {

        List<String[]> employeesList = CustomCSVRead.readCSVFile(getFilePathEmployees());
        Employee employee = new Employee();
        for (String[] str : employeesList) {
            if ((str[0]).equals(id)) {
                employee.setId(id);
                employee.setNameEmployee(str[1]);
                employee.setLastNameEmployee(str[2]);
                employee.setAge(Integer.parseInt(str[3]));

                return employee;
            }
        }
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        List<String[]> listCsvFile = CustomCSVRead.readCSVFile(getFilePathEmployees());
        List<Employee> employeesList = new ArrayList<>();

        for (String[] str : listCsvFile) {
            employeesList.add(Employee.parserStringToDepartment(str));
            return employeesList;
        }
        return employeesList;
    }

    private static String generateId() {
        String id = UUID.randomUUID().toString();
        return id;
    }
}