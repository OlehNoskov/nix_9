package ua.com.alevel.db.impl;

import ua.com.alevel.CustomCSVRead;
import ua.com.alevel.CustomCSVWrite;
import ua.com.alevel.db.EmployeeDB;
import ua.com.alevel.entity.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EmployeeDBImpl implements EmployeeDB {

    private static final String FILE_PATH_EMPLOYEE = "employees.csv";
    private static List<String[]> employees;
    private static EmployeeDBImpl instance;
    private static String[] header = {"id", "name", "lastName", "age"};

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

    public static String[] getHeaderCSVFile() {
        return header;
    }

    @Override
    public void create(Employee employee) {
        employee.setId(generateId());
        employees.add(Employee.parserToStringEmployee(employee));
        CustomCSVWrite.writeToCSVFile(employees, EmployeeDBImpl.getFilePathEmployees(), true);
        System.out.println("Сотрудник " + employee.getNameEmployee() + " " + employee.getLastNameEmployee() + " успешно создан!");
        employees.clear();
    }

    @Override
    public void update(Employee employee) {
        Employee updateEmployee = findByID(employee.getId());
        updateEmployee.setNameEmployee(employee.getNameEmployee());
        updateEmployee.setLastNameEmployee(employee.getLastNameEmployee());
        updateEmployee.setAge(employee.getAge());

        List<String[]> temp = CustomCSVRead.readCSVFile(EmployeeDBImpl.getFilePathEmployees());
        for (int i = 0; i < temp.size(); i++) {
            for (int b = 1; b < temp.get(i).length; b++) {
                if (temp.get(i)[0].equals(employee.getId())) {
                    temp.get(i)[1] = employee.getNameEmployee();
                    temp.get(i)[2] = employee.getLastNameEmployee();
                    temp.get(i)[3] = employee.getAge() + "";
                    employees.add(temp.get(i));
                } else {
                    employees.add(temp.get(i));
                }
            }
        }
        CustomCSVWrite.writeToCSVFile(employees, EmployeeDBImpl.getFilePathEmployees(), false);
        System.out.println("Данные успешно обновлены!");
        employees.clear();
    }

    @Override
    public void delete(String id) {
        List<String[]> removeEmployees = new ArrayList<>();
        List<String[]> employeesList = new ArrayList<>(CustomCSVRead.readCSVFile(getFilePathEmployees()));

        for (String[] str : employeesList) {
            for (int i = 0; i < str.length; i++) {
                if (id.equals(str[0])) {
                    removeEmployees.add(str);
                }
            }
        }
        if (!removeEmployees.isEmpty()) {
            System.out.println("Сотрудник " + removeEmployees.get(0)[1] + " " + removeEmployees.get(0)[2] + " был успешно удален!");
            employeesList.removeAll(removeEmployees);
            CustomCSVWrite.writeToCSVFile(employeesList, getFilePathEmployees(), false);
        } else
            System.out.println("Сотрудник с данным id не найден!");
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

        for (int i = 1; i < listCsvFile.size(); i++) {
            employeesList.add(Employee.parserStringToEmployee(listCsvFile.get(i)));
        }
        for (int i = 0; i < employeesList.size(); i++) {
            System.out.println(employeesList.get(i).toString());
        }
        return employeesList;
    }

    private static String generateId() {
        String id = UUID.randomUUID().toString();
        return id;
    }
}