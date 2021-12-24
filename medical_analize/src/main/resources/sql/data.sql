# insert into users values (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true,doctor3@mail.ru, 'Oleg', 'Noskov','MALE');
# insert into users values (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Sergey', 'Funtusov','MALE');
# insert into users values (3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Irina', 'Cventuh','FEMALE');
# insert into users values (4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Pavel', 'Noskov','MALE');
# insert into users values (5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Igor', 'Babakin','MALE');
# insert into users values (6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Olegs', 'Noskov','MALE');
# insert into users values (7, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Natasha', 'Legusha','FEMALE');
# insert into users values (8, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Olga', 'Zvereva','FEMALE');
# insert into users values (9, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Oleg', 'Unicum','MALE');
# insert into users values (10, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Omar', 'Eps','MALE');


# insert into patients values (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Oleg', 'Noskov',0669518856,'MALE');
# insert into patients values (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Sergey', 'Funtusov',0669518856,'MALE');
# insert into patients values (3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Irina', 'Cventuh',0669518856,'FEMALE');
# insert into patients values (4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Pavel', 'Noskov',0669518851,'MALE');
# insert into patients values (5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Igor', 'Babakin',0669518856,'MALE');
# insert into patients values (6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Olegs', 'Noskov',0669518856,'MALE');
# insert into patients values (7, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Natasha', 'Legusha',0669518856,'FEMALE');
# insert into patients values (8, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Olga', 'Zvereva',0669518856,'FEMALE');
# insert into patients values (9, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Oleg', 'Unicum',0669518856,'MALE');
# insert into patients values (10, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Omar', 'Eps',0669518856,'MALE');
# insert into patients values (11, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Sergey', 'Maltsev',0669518856,'MALE');
# insert into patients values (12, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Ilona', 'Prihodko',0669518856,'FEMALE');
# insert into patients values (13, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Katia', 'Sherbina',0669518856,'FEMALE');
# insert into patients values (14, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Irina', 'Rastvorceva',0669518856,'FEMALE');
# insert into patients values (15, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Sergey', 'Kyi',19,'MALE');
# insert into patients values (16, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Nikolay', 'Shevchenko',0669518856,'MALE');
# insert into patients values (17, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Sergey', 'Kysil',0669518856,'MALE');
# insert into patients values (18, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Iegor', 'Funtusov',0669518856,'MALE');
# insert into patients values (19, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Oleg', 'Family',0669518856,'MALE');
# insert into patients values (20, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, 'Sergey', 'Noskov',0669518856,'MALE');


insert into orders values (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true,1,8);
insert into orders values (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true,2,9);
insert into orders values (3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true,3,10);
insert into orders values (4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true,4,11);
insert into orders values (5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true,5,12);
insert into orders values (6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true,6,13);
insert into orders values (7, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true,7,13);
insert into orders values (8, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true,8,14);
insert into orders values (9, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true,9,15);
insert into orders values (10, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true,10,16);
insert into orders values (11, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true,12,17);



insert into doctor_patient values (1, 8);
insert into doctor_patient values (1, 9);
insert into doctor_patient values (1, 10);
insert into doctor_patient values (1, 11);
insert into doctor_patient values (2, 8);
insert into doctor_patient values (2, 14);
insert into doctor_patient values (2, 15);
insert into doctor_patient values (4, 9);
insert into doctor_patient values (4, 14);
insert into doctor_patient values (5, 15);
insert into doctor_patient values (5, 16);
insert into doctor_patient values (6, 9);
insert into doctor_patient values (6, 11);
insert into doctor_patient values (6, 15);
insert into doctor_patient values (6, 17);
insert into doctor_patient values (7, 8);
insert into doctor_patient values (7, 10);
insert into doctor_patient values (7, 12);
