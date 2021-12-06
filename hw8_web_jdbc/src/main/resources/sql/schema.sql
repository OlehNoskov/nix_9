DROP TABLE course IF EXISTS course;
DROP TABLE students IF EXISTS students;
DROP TABLE course_student IF EXISTS course_student;

create table course
(
    id         bigint auto_increment
        primary key,
    created    datetime(6) null,
    updated    datetime(6) null,
    visible    bit null,
    name varchar(255) not null

);

create table students
(
    id               bigint auto_increment
        primary key,
    created          datetime(6)  null,
    updated          datetime(6)  null,
    visible          bit          null,
    first_name        varchar(255) not null,
    last_name         text         null,
    age              int          not null

);

create table course_student
(
    course_id bigint not null,
    student_id   bigint not null,
    primary key (course_id, student_id),
    foreign key (course_id) references course (id),
    foreign key (course_id) references students (id)
);