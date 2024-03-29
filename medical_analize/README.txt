Приложение  - "Personal doctor"

Использованные технологии:
Spring Boot
Spring JPA
Spring Web
Spring Security
Hibernate
Thymeleaf (HTML), CSS, Bootstrap, JS
MySQL

Целью данного серивиса является обращение клиентом и оказание им медицинских рекомендаций по нескольким направлениям:
- создание списка рекомендуемых витанов и минералов в зависимости от целей и состояния организма клиента;
- создание персонального списка анализов для поиска проблемы недуга и ухуджения здоровья, с которым столкнулся пациент;
- превичное обращение к доктору и назначение им списка рекомендуемых лабораторных, инструментальных исследований,
посещение профильных врачей, перечень анализов для поиска причины ухудшения здоровья пациента.

На главной страничке реализована общая информация о сервисе, а также есть небольшой перечень научных публикаций.

Стоимость услуг фиксированная и основанная на сложности и времени, уделяемое среднее рабочее время доктором для работы над каждым случаем.

В персональном кабинете пациента хранятся список выполненных услуг, разделенных на категории, которые он может посмотреть в любое время.
Также пациент в заказе может видеть какой доктор выполнял его заказ, и написать отзыв о его работе.
В кабинете пациента также есть раздел, который поможет подготовиться к сдаче анализов по разным направлениям.
Также есть возможность посмотреть пример готового заказа, скачав файл!

В персональном кабинете доктора реализован доступ к заказам пациентам.
Заказы пациентом становяться доступными доктору в случае оплаты пациентом.
Заказы, которые выполненные доктором переходят в статус "Выполнено" и больше не доступны доктору.
На главной странице доктора есть небольшая статистика его работы:
- количество выполненных заказов
- количество выполненных заказов по категориям
- Сумма зарплаты доктора (От суммы заказа - 75%)

В песональном кабинете администратора реализовано:
 Общая статистика заказов:
- количество выполненных заказов (разбитые по категориям)
- количество заказов находящихся в работе (разбитые по категориям)
- общее количество пользователей приложения (разбитые по категориям)
- общий доход
- чистая прибыль с вычетом зарплаты докторам (25% от стоимости услуг).

Администратор имеет доступ к личной информации пользователей, может изменять персональные данные, удалять пользователей.
Адимнистратор имеет доступ к отзывам, созданные пациетнами, может их просматривать, удалять.

Таблица в MySql: medical_analize;
Создание таблиц происходит при запуске;
Для заполнения базы нужно использовать скрипт: data.sql;
---------------
Пользователи:
логин: admin@mail.com
пароль: rootroot

логин: patient@mail.com
пароль: rootroot

логин: doctor@mail.com
пароль: rootroot