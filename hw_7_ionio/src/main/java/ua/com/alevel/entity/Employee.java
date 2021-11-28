package ua.com.alevel.entity;

public class Employee extends BaseEntity {

    private String nameEmployee;
    private String lastNameEmployee;
    private Integer age;

    public Employee(){
        super();
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getLastNameEmployee() {
        return lastNameEmployee;
    }

    public void setLastNameEmployee(String lastNameEmployee) {
        this.lastNameEmployee = lastNameEmployee;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "nameEmployee='" + nameEmployee + '\'' +
                ", lastNameEmployee='" + lastNameEmployee + '\'' +
                ", age=" + age +
                '}';
    }

    public static String[] parserToStringEmployee(Employee employee){
        String[] employeeArray = new String[4];
        employeeArray[0] = employee.getId();
        employeeArray[1] = employee.getNameEmployee();
        employeeArray[2] = employee.getLastNameEmployee();
        employeeArray[3] = String.valueOf(employee.getAge());
        return employeeArray;
    }
}