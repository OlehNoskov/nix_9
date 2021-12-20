//package ua.com.alevel.persistence.repository;
//
//import org.springframework.stereotype.Repository;
//import ua.com.alevel.persistence.entity.Group;
//
//@Repository
//public interface GroupRepository extends AbstractRepository<Group>{
//    Set<Group> findByDepartmentTypeOrCreatedBetween(DepartmentType departmentType, Date start, Date end);
//    List<Department> findByNameStartingWithIgnoreCase(String name);
//    Set<Department> findByVisibleTrue();
//
//    List<Department> findByDepartmentTypeIn(List<DepartmentType> departmentTypes);
//
//    //    select * from departments left join dep_emp de on departments.id = de.dep_id where de.emp_id in (1,2);
//    @Query(value = "select distinct(d) from Department as d join d.employees as de where de.id in :ids")
//    List<Department> findByEmployees(@Param("ids") Set<Long> employees);
//
//    @Query(value = "select d from Department as d join d.employees as de where de.id in :ids")
//    Set<Department> findByEmployeesIds(@Param("ids") Set<Long> employees);
//
//    @Query(value = "select new ua.com.alevel.persistence.entitydto.DepEmp(" +
//            "(select count(d) from Department d where d.departmentType = 'JAVA')," +
//            "(select count(d) from Department d where d.departmentType = 'JS') )" +
//            "from Department d group by d.departmentType")
//    Set<DepEmp> findCountByJSAndJava();
//}
