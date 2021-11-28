package ua.com.alevel.entity;

public class Department extends BaseEntity {

    private String departmentName;

    public Department(){
        super();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department: " +
                "departmentTitle = " + departmentName;
    }

    public static String[] parserToStringDepartment(Department department){
        String[] departmentArray = new String[2];
        departmentArray[0] = department.getId();
        departmentArray[1] = department.getDepartmentName();
        return departmentArray;
    }
}