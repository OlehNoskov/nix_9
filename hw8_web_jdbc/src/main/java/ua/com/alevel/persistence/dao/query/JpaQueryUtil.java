package ua.com.alevel.persistence.dao.query;

public class JpaQueryUtil {
    private JpaQueryUtil() { }

    public static final String CREATE_GROUP_QUERY = "INSERT INTO course VALUES(default,?,?,?,?)";
    public static final String UPDATE_BY_ID_QUERY = "UPDATE course SET name = ?, updated = ? WHERE id = ";
    public static final String DELETE_GROUP_BY_ID_QUERY = "DELETE FROM course WHERE id = ";
//    public static final String EXIST_GROUP_BY_ID_QUERY = "SELECT COUNT(*) FROM course WHERE id = ";
    public static final String FIND_ALL_GROUPS_QUERY = "SELECT * FROM course";
    public static final String FIND_GROUP_BY_ID_QUERY = "SELECT * FROM course WHERE id = ";

    public static final String CREATE_STUDENT_QUERY = "INSERT INTO students VALUES(default,?,?,?,?,?,?)";
    public static final String UPDATE_STUDENT_BY_ID_QUERY = "UPDATE students SET first_name = ?, last_name = ?,age = ?, updated = ? WHERE id = ";
    public static final String DELETE_STUDENT_BY_ID_QUERY = "DELETE FROM students WHERE id = ";
    public static final String DELETE_STUDENTS_BY_GROUP_ID_QUERY = "DELETE FROM course_student WHERE course_id = ";
    public static final String EXIST_STUDENT_BY_ID_QUERY = "SELECT COUNT(*) FROM students WHERE id = ";
    public static final String FIND_STUDENT_BY_ID_QUERY = "SELECT * FROM students AS stud JOIN course AS course ON student.course_id = course.id WHERE student.id = ";
    public static final String FIND_ALL_STUDENTS_QUERY = "SELECT * FROM students AS stud JOIN course AS course ON stud.course_id = course.id";
    public static final String FIND_ALL_STUDENTS_BY_GROUP_ID_QUERY = "SELECT * FROM students AS stud JOIN course AS course ON stud.course_id = course.id WHERE stud.course_id = ";
}