drop table if exists items;
create table if not exists items
(
    id          serial primary key not null,
    description varchar(255)       not null,
    created     timestamp          not null,
    done        boolean,
    user_id int not null references users (id)
);
drop table if exists roles;
create table if not exists roles
(
    id   serial primary key,
    name varchar(2000)
);
insert into roles (name)
values ('Администратор'),
       ('Модератор'),
       ('Пользователь'),
       ('Гость');
drop table if exists users;
create table if not exists users
(
    id       serial primary key,
    name     varchar(2000),
    password varchar(64),
    role_id  int not null references roles (id)
);
insert into users (name, password, role_id)
values ('admin', 'admin', 1),
       ('user', 'user', 3);
