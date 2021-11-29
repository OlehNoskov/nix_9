package ua.com.alevel;

import ua.com.alevel.controller.impl.BaseControllerImpl;
import ua.com.alevel.db.impl.DepartmentDBImpl;
import ua.com.alevel.db.impl.EmployeeDBImpl;
import ua.com.alevel.entity.Department;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.service.DepartmentService;
import ua.com.alevel.service.impl.DepartmentServiceImpl;

import java.io.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        new BaseControllerImpl().run();

//        List<String[]> data = new ArrayList<>();
//        List<String[]> data2 = new ArrayList<>();
//        data.add(DepartmentDBImpl.getHeaderCSVFile());
//
//
//        DepartmentService departmentService = new DepartmentServiceImpl();
//
//        Department department = new Department();
//        department.setDepartmentName("Молодежи и Спорта");
//        departmentService.create(department);
//        data.add(Department.parserToStringDepartment(department));

//        CustomCSVWrite.writeToCSVFile(data, "hw_7_ionio/departments.csv", true);
//
//        Employee employee = new Employee();
//        employee.setId("1");
//        employee.setNameEmployee("Oleh");
//        employee.setLastNameEmployee("Noskov");
//        employee.setAge(28);
//        data2.add(EmployeeDBImpl.getHeaderCSVFile());
//        data2.add(Employee.parserToStringEmployee(employee));
//
//        CustomCSVWrite.writeToCSVFile(data,"hw_7_ionio/departments.csv", true);
    }
}