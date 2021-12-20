DROP  SCHEMA IF EXISTS mydbtest;

CREATE SCHEMA mydbtest DEFAULT CHARACTER SET utf8 ;

DROP TABLE mydbtest.course;
DROP TABLE mydbtest.students;
DROP TABLE course_student;

CREATE TABLE mydbtest.course
(
    id      BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    created DATETIME(6)  NULL,
    updated DATETIME(6)  NULL,
    visible BIT          null,
    name    VARCHAR(255) NOT NULL

);

create table mydbtest.students
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

create table mydbtest.course_student
(
    course_id  BIGINT NOT NULL,
    student_id BIGINT NOT NULL,
    PRIMARY key (course_id, student_id),
    FOREIGN KEY (course_id) REFERENCES course (id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES students (id) ON DELETE CASCADE
);