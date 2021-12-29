create table users
(
    id                  serial
        constraint users_pkey
            primary key,
    created             timestamp(6),
    updated             timestamp(6),
    user_first_name     varchar(255)   not null,
    user_second_name     varchar(255)   not null,
    user_age     BIGINT         not null
);

create table accounts
(
    id                  serial
        constraint accounts_pkey
            primary key,
    created             timestamp(6),
    updated             timestamp(6),
    user_id             int            not null ,
    account_numbers     varchar(255)   not null,
    account_balance     BIGINT         not null,
    foreign key (user_id) references users (id) ON DELETE CASCADE
);

create table categories
(
    id                  serial
        constraint categories_pkey
            primary key,
    created                     timestamp(6),
    updated                     timestamp(6),
    category_name               varchar(255)   not null,
    category_income_expense     BOOLEAN
);

create table transactions
(
    id                  serial
        constraint transactions_pkey
            primary key,
    created                     timestamp(6),
    updated                     timestamp(6),
    user_id                     int            not null ,
    account_id                  int            not null ,
    category_id                 int,
    transaction_sum             BIGINT         not null ,
    user_name                   varchar(255),
    account_numbers             varchar(255),
    category_name               varchar(255),
    category_income_expense     varchar(255),
    foreign key (user_id) references users (id) ON DELETE CASCADE,
    foreign key (account_id) references accounts (id) ON DELETE CASCADE
);