CREATE SCHEMA `mydbtest`;

DROP TABLE course IF EXISTS course;
DROP TABLE students IF EXISTS students;
DROP TABLE course_student IF EXISTS course_student;

CREATE TABLE course2
(
    id      BIGINT AUTO_INCREMENT
            PRIMARY KEY,
    created DATETIME(6)  NULL,
    updated DATETIME(6)  NULL,
    visible BIT          null,
    name    VARCHAR(255) NOT NULL,
    group_type VARCHAR(255) NOT NULL

);

create table students2
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

create table course2_students2
(
    course_id  BIGINT NOT NULL,
    students_id BIGINT NOT NULL,
    PRIMARY key (course_id, students_id),
    FOREIGN KEY (course_id) REFERENCES course2 (id) ON DELETE CASCADE,
    FOREIGN KEY (students_id) REFERENCES students2 (id) ON DELETE CASCADE
);