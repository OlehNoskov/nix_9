package ua.com.alevel.controller.impl;

import ua.com.alevel.CustomCSVWrite;
import ua.com.alevel.controller.EmployeeController;

import ua.com.alevel.db.impl.DepartmentDBImpl;
import ua.com.alevel.entity.Employee;

import ua.com.alevel.service.DepartmentService;
import ua.com.alevel.service.EmployeeService;
import ua.com.alevel.service.impl.DepartmentServiceImpl;
import ua.com.alevel.service.impl.EmployeeServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class EmployeeControllerImpl implements EmployeeController {

    EmployeeService employeeService = new EmployeeServiceImpl();
    DepartmentService departmentService = new DepartmentServiceImpl();
    String[] headerDep_Emp_Table = {"ID DEPARTMENT", "ID EMPLOYEE"};
    private static List<String[]> idDepAndEmp = new ArrayList<>();

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String position;
        try {
            showMenu();
            while ((position = reader.readLine()) != null && !position.equals("0")) {
                crud(position, reader);
            }
        } catch (IOException e) {
            System.out.println("Ошибка: = " + e.getMessage());
        }
    }

    private void showMenu() {
        System.out.println();
        System.out.println("===== МЕНЮ Сотрудника =====");
        System.out.println("Нажмите 1: Создание нового сотрудника");
        System.out.println("Нажмите 2: Изменения данных сотрудника");
        System.out.println("Нажмите 3: Удаление сотрудника");
        System.out.println("Нажмите 4: Поиска сотрудника по Id");
        System.out.println("Нажмите 5: Поиск всех сотрудников");
        System.out.println("Нажмите 6: Поиск сотрудника по указанному департаменту");
        System.out.println("Нажмите 0: Выхода в Главное Меню");
    }

    private void crud(String choiceMenu, BufferedReader reader) {
        switch (choiceMenu) {
            case "1":
                create(reader);
                break;
            case "2":
                update(reader);
                break;
            case "3":
                delete(reader);
                break;
            case "4":
                findById(reader);
                break;
            case "5":
                findAllEmployee();
                break;
        }
        showMenu();
    }

    private void create(BufferedReader reader) {
        System.out.println("=== Создание сотрудника ===");
        try {
            System.out.println("Введите имя нового сотрудника:");
            String name = reader.readLine();
            System.out.println("Введите фамилию нового сотрудника:");
            String lastname = reader.readLine();
            System.out.println("Введите возраст нового сотрудника:");
            String ageEmployee = reader.readLine();
            try {
                int age = Integer.parseInt(ageEmployee);
            } catch (Exception e) {
                System.out.println("Некорректный возраст!");
                return;
            }
            System.out.println("Выберите департамент для работы сотрудника:");
            String idDepartment = reader.readLine();
            Employee employee = new Employee();
            String idDep = departmentService.findByID(idDepartment).getId();
            if (idDepartment == null) {
                System.out.println("Некорректный id!");
                return;
            }
            employee.setNameEmployee(name);
            employee.setLastNameEmployee(lastname);
            employee.setAge(Integer.parseInt(ageEmployee));
            employeeService.create(employee);

            String[] idDep_Emp = new String[2];
            idDep_Emp[0] = departmentService.findByID(idDepartment).getId();
            idDep_Emp[1] = employee.getId();
            idDepAndEmp.add(idDep_Emp);
            CustomCSVWrite.writeToCSVFile(idDepAndEmp, DepartmentDBImpl.FILE_PATH_EMPLOYEES_FOR_DEPARTMENT, true);
        } catch (IOException e) {
            System.out.println("Что то пошло не так: " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        System.out.println("=== Изменение данных сотрудника ===");
        System.out.println("Введите Id сотрудника, которого хотите изменить:");
        try {
            String idEmployee = reader.readLine();
            Employee employee = employeeService.findByID(idEmployee);
            if (employee == null) {
                System.out.println("Id не был введен корректно");
                return;
            }
            System.out.println("Введите новое имя сотрудника:");
            String name = reader.readLine();
            if (name != null) {
                System.out.println("Введите новую фамилию сотрудника:");
                String lastname = reader.readLine();
                System.out.println("Введите возраст сотрудника:");
                String age = reader.readLine();

                employee.setLastNameEmployee(lastname);
                employee.setNameEmployee(name);
                employee.setAge(Integer.parseInt(age));
                employeeService.update(employee);
            } else {
                System.out.println("Вы не ввели новое имя!");
            }
        } catch (IOException e) {
            System.out.println("Ошибка " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        System.out.println("Введите Id сотрудника, которого хотите удалить:");
        try {
            String idEmployee = reader.readLine();
            if (!idEmployee.isEmpty()) {
                Employee employee;
                employee = employeeService.findByID(idEmployee);
                if (employee == null) {
                    System.out.println("Некорректный ID");
                    return;
                } else {
                    employeeService.delete(idEmployee);
                }
            } else {
                System.out.println("Сотрудник не найден!");
            }
        } catch (IOException e) {
            System.out.println("Ошибка " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        System.out.println("=== Поиск сотрудника ===");
        Employee employee;
        System.out.println("Введите ID сотрудника, которого хотите найти:");
        try {
            String idEmployee = reader.readLine();
            if (!idEmployee.isEmpty()) {
                employee = employeeService.findByID(idEmployee);
                if (employee == null) {
                    System.out.println("Некорректный ID");
                } else
                    System.out.println(employee);
            }
        } catch (IOException e) {
            System.out.println("Error+ " + e.getMessage());
        }
    }

    private void findAllEmployee() {
        System.out.println("=== Поиск всех сотрудников ===");
        employeeService.findByAll();
    }
}