DROP TABLE course IF EXISTS course;
DROP TABLE students IF EXISTS students;
DROP TABLE course_student IF EXISTS course_student;

CREATE TABLE courses
(
    id      BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    created DATETIME(6)  NULL,
    updated DATETIME(6)  NULL,
    visible BIT          null,
    name    VARCHAR(255) NOT NULL

);

create table students
(
    id         BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    created    DATETIME(6)  NULL,
    updated    DATETIME(6)  NULL,
    visible    BIT          null,
    first_name VARCHAR(255) NOT NULL,
    last_name  TEXT         null,
    age        INT          NOT NULL
);

create table course_student
(
    course_id  BIGINT NOT NULL,
    student_id BIGINT NOT NULL,
    PRIMARY key (course_id, student_id),
    FOREIGN KEY (course_id) REFERENCES courses (id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES students (id) ON DELETE CASCADE
);