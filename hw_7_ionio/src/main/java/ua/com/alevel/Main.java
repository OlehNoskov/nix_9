package ua.com.alevel;

import ua.com.alevel.db.impl.DepartmentDBImpl;
import ua.com.alevel.entity.Department;
import ua.com.alevel.service.DepartmentService;
import ua.com.alevel.service.impl.DepartmentServiceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        new BaseControllerImpl().run();

        List<String[]> data = new ArrayList<>();
//        data.add(DepartmentDBImpl.getHeaderCSVFile());



        DepartmentService departmentService = new DepartmentServiceImpl();


//        String[] strings1 = {"1", "Oleg1", "Noskov1", "28"};
//        String[] strings2 = {"2","Oleg1","Noskov1", "28"};
//        String[] strings3 = {"3","Oleg1","Noskov1", "28"};
//        String[] strings4 = {"4","Oleg1","Noskov1", "28"};
//        data.add(strings1);
//        data.add(strings2);
//        data.add(strings3);
//        data.add(strings4);


        Department department = new Department();
        department.setId("1");
        department.setDepartmentName("Молодежи и Спорта");
        departmentService.create(department);
        data.add(Department.parserToStringDepartment(department));


//        Department department2 = new Department();
//        department.setId("2");
//        department.setDepartmentName("Oleh2");
//        departmentService.create(department2);


//

            CustomCSVWrite.writeToCSVFile(data, "hw_7_ionio/departments.csv", true);
        CustomCSVRead.readCSVFile("hw_7_ionio/departments.csv");
        DepartmentService departmentService1 = new DepartmentServiceImpl();
        Department department1 = departmentService1.findByID("09aca9e6-b59a-47a3-957e-f0c46e5a24c5");

        System.out.println(department1.toString());

        }

}
