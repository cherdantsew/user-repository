create table users(
    id bigserial primary key not null,
    login varchar not null,
    password varchar not null
);