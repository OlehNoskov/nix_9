insert into users
values (PATIENT, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, null, UKRAINE, patient1@mail.ru, true, 'Oleg',
        'Noskov', $2a$10$hs5xMl8kXILtXGP8sVw.PuKIoZ/4vdXZ6qU5Y8f265YrvCsdHHjMG, +380991560574, ROLE_PATIENT, 'MALE',
        175, 75);
insert into users
values (PATIENT,3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, null, RUSSIA, patient2@mail.ru, true, 'Sergey',
        'Funtusov', $2a$10$5bdIWys1Dj1z0hXcLKZK2.JUoBCgU0xPy.SFESUPBXfft4XvIJz4O, +380991560574, ROLE_PATIENT, 'MALE',
        180, 100);
insert into users
values (PATIENT,4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, null, SPAIN, patient3@mail.ru, true, 'Irina',
        'Cventuh', $2a$10$hs5xMl8kXILtXGP8sVw.PuKIoZ/4vdXZ6qU5Y8f265YrvCsdHHjMG, +380991560574, ROLE_PATIENT, 'FEMALE',
        160, 50);
insert into users
values (DOCTOR,5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, null, POLAND, doctor1@mail.ru, true, 'Pavel',
        'Noskov', $2a$10$g0nkPTduyxDrL.c1Poy5W.QxyfwsR6yJ4nqO1CFBAuY73JqacYfiK, +380991560574, ROLE_DOCTOR, 'MALE',
        null, null);
insert into users
values (DOCTOR,6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true, null, UKRAINE, doctor2@mail.ru, true, 'Igor',
        'Babakin', $2a$10$O56iz58N4CzWOGnOh3D8q.wfG1hjT8m4hBE/fzp5UH4cFnN5Jj4ni, +380991560574, ROLE_DOCTOR, 'MALE',
        null, null);

