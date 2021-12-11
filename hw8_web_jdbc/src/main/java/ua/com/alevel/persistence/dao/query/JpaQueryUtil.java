package ua.com.alevel.persistence.dao.query;

public class JpaQueryUtil {
    private JpaQueryUtil() { }

    public static final String CREATE_GROUP_QUERY = "INSERT INTO course VALUES(default,?,?,?,?)";
    public static final String UPDATE_BY_ID_QUERY = "UPDATE course SET name = ?, updated = ? WHERE id = ";
    public static final String DELETE_GROUP_BY_ID_QUERY = "DELETE FROM course WHERE id = ";
    public static final String EXIST_GROUP_BY_ID_QUERY = "SELECT COUNT(*) FROM course WHERE id = ";
    public static final String FIND_ALL_GROUPS_QUERY = "SELECT * FROM course";
    public static final String FIND_GROUP_BY_ID_QUERY = "SELECT * FROM course WHERE id = ";
    public static final String FIND_ALL_GROUPS_BY_STUDENT_ID_QUERY = "SELECT * FROM course AS c JOIN course_student AS cs ON c.id = cs.course_id WHERE c.id =";

    public static final String CREATE_STUDENT_QUERY = "INSERT INTO students VALUES(default,?,?,?,?,?,?)";
    public static final String UPDATE_STUDENT_BY_ID_QUERY = "UPDATE students SET first_name = ?, last_name = ?,age = ?, updated = ? WHERE id = ";
    public static final String DELETE_STUDENT_BY_ID_QUERY = "DELETE FROM students WHERE id = ";
    public static final String EXIST_STUDENT_BY_ID_QUERY = "SELECT COUNT(*) FROM students WHERE id = ";
    public static final String FIND_STUDENT_BY_ID_QUERY = "SELECT * FROM students WHERE id = ";
    public static final String FIND_ALL_STUDENTS_QUERY = "SELECT * FROM students";
    public static final String FIND_ALL_STUDENTS_BY_GROUP_ID_QUERY = "SELECT * FROM students AS s JOIN course_student AS cs ON s.id = cs.student_id WHERE s.id =";
}