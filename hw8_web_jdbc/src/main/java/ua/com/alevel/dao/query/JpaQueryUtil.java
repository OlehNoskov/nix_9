package ua.com.alevel.dao.query;

public class JpaQueryUtil {
    private JpaQueryUtil() { }

    public static final String CREATE_GROUP_QUERY = "INSERT INTO groups VALUES(default,?,?,?,?)";
    public static final String UPDATE_BY_ID_QUERY = "UPDATE groups SET name = ?, group_type = ?, updated = ? WHERE id = ";
    public static final String DELETE_GROUP_BY_ID_QUERY = "DELETE FROM groups WHERE id = ";
    public static final String EXIST_GROUP_BY_ID_QUERY = "SELECT COUNT(*) FROM groups WHERE id = ";
    public static final String FIND_ALL_GROUPS_QUERY = "SELECT * FROM groups";
    public static final String FIND_GROUP_BY_ID_QUERY = "SELECT * FROM groups WHERE id = ";

    public static final String CREATE_STUDENT_QUERY = "INSERT INTO students VALUES(default,?,?,?,?,?,?)";
    public static final String UPDATE_STUDENT_BY_ID_QUERY = "UPDATE students SET first_name = ?, last_name = ?,age = ?, updated = ? WHERE id = ";
    public static final String DELETE_STUDENT_BY_ID_QUERY = "DELETE FROM students WHERE id = ";
    public static final String DELETE_STUDENTS_BY_GROUP_ID_QUERY = "DELETE FROM students WHERE group_id = ";
    public static final String EXIST_STUDENT_BY_ID_QUERY = "SELECT COUNT(*) FROM students WHERE id = ";
    public static final String FIND_STUDENT_BY_ID_QUERY = "SELECT * FROM students AS stud JOIN groups AS group ON student.group_id = group.id WHERE student.id = ";
    public static final String FIND_ALL_STUDENTS_QUERY = "SELECT * FROM students AS stud JOIN groups AS group ON stud.group_id = group.id";
    public static final String FIND_ALL_STUDENTS_BY_GROUP_ID_QUERY = "SELECT * FROM students AS stud JOIN groups AS group ON stud.group_id = group.id WHERE stud.group_id = ";
}