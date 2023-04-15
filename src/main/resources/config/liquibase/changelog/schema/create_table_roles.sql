create table roles(
    id smallserial primary key not null,
    role_name varchar not null,
    authorities varchar default ''
);