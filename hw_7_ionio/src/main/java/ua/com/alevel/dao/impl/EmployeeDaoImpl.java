//package ua.com.alevel.dao.impl;
//
//import ua.com.alevel.dao.EmployeeDao;
//import ua.com.alevel.db.impl.EmployeeDBImpl;
//import ua.com.alevel.entity.Employee;
//
//import java.util.Collection;
//
//public class EmployeeDaoImpl implements EmployeeDao {
//
//    private final EmployeeDBImpl employeeDB = EmployeeDBImpl.getInstance();
//
//    @Override
//    public void create(Employee entity) {
//        employeeDB.create(entity);
//    }
//
//    @Override
//    public void update(Employee entity) {
//        employeeDB.update(entity);
//    }
//
//    @Override
//    public void delete(String id) {
//        employeeDB.delete(id);
//    }
//
//    @Override
//    public Employee findByID(String id) {
//        return employeeDB.findByID(id);
//    }
//
//    @Override
//    public Collection<Employee> findAll() {
//        return employeeDB.findAll();
//    }
//}