insert into courses values (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Culture');
insert into courses values (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Sport');
insert into courses values (3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Building');
insert into courses values (4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Art');
insert into courses values (5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Math');
insert into courses values (6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'History');
insert into courses values (7, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Biology');
insert into courses values (8, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Chemistry');
insert into courses values (9, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Transport');
insert into courses values (10, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Economics');


insert into students values (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Oleg', 'Noskov',28);
insert into students values (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Sergey', 'Funtusov',30);
insert into students values (3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Irina', 'Cventuh',20);
insert into students values (4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Pavel', 'Noskov',18);
insert into students values (5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Igor', 'Babakin',18);
insert into students values (6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Olegs', 'Noskov',18);
insert into students values (7, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Natasha', 'Legusha',22);
insert into students values (8, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Olga', 'Zvereva',23);
insert into students values (9, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Oleg', 'Unicum',21);
insert into students values (10, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Omar', 'Eps',29);
insert into students values (11, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Sergey', 'Maltsev',30);
insert into students values (12, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Ilona', 'Prihodko',27);
insert into students values (13, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Katia', 'Sherbina',24);
insert into students values (14, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Irina', 'Rastvorceva',20);
insert into students values (15, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Sergey', 'Kyi',19);
insert into students values (16, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Nikolay', 'Shevchenko',21);
insert into students values (17, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Sergey', 'Kysil',23);
insert into students values (18, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Iegor', 'Funtusov',24);
insert into students values (19, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Oleg', 'Family',22);
insert into students values (20, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Sergey', 'Noskov',20);
insert into students values (21, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Test', 'Test',20);


insert into course_student values (1, 1);
insert into course_student values (1, 3);
insert into course_student values (1, 9);
insert into course_student values (1, 15);
insert into course_student values (2, 3);
insert into course_student values (2, 13);
insert into course_student values (2, 19);
insert into course_student values (3, 4);
insert into course_student values (3, 5);
insert into course_student values (4, 13);
insert into course_student values (4, 14);
insert into course_student values (5, 5);
insert into course_student values (5, 15);
insert into course_student values (5, 18);
insert into course_student values (5, 16);
insert into course_student values (6, 7);
insert into course_student values (6, 17);
insert into course_student values (6, 20);
insert into course_student values (7, 6);
insert into course_student values (7, 12);
insert into course_student values (7, 17);
insert into course_student values (8, 2);
insert into course_student values (8, 10);
insert into course_student values (8, 20);
insert into course_student values (9, 8);
insert into course_student values (9, 14);
insert into course_student values (9, 18);
insert into course_student values (10, 8);
insert into course_student values (10, 16);
insert into course_student values (10, 19);